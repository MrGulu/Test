package distributedLock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * @author tangwenlong
 * @description: 分布式锁的实现
 * @CreateDate: 2020-01-15
 */
@Slf4j
public class DistributedLockTest {

    /**
     * 使用zookeeper实现分布式锁，借助curator框架实现。
     * <p>
     * 四种锁方案：
     * InterProcessMutex：分布式可重入排它锁
     * InterProcessSemaphoreMutex：分布式排它锁
     * InterProcessReadWriteLock：分布式读写锁
     * InterProcessMultiLock：将多个锁作为单个实体管理的容器
     */
    @Test
    @SuppressWarnings("all")
    public void zookeeperLockTest() {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //测试zookeeper单节点
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        //测试zookeeper集群
//        CuratorFramework client = CuratorFrameworkFactory.newClient("10.169.169.231:3181,10.169.169.231:3182,10.169.169.231:3183", retryPolicy);
        client.start();
        //创建分布式锁（InterProcessMutex可重入锁），锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        Thread thread1 = new Thread(() -> {
            try {
                //获得锁，若没有获得锁，一直等待
                //锁被占用时永久阻塞等待
                //这里有个地方需要注意，当与zookeeper通信存在异常时，acquire会直接抛出异常，需要使用者自身做重试策略。
                mutex.acquire();
                System.out.println(Thread.currentThread().getName() + ":acquire lock！");
                //模拟耗时操作
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //重入锁，第一次获得后，再次获得时，由于是一个线程，所以只是计数+1
                mutex.acquire();
                System.out.println(Thread.currentThread().getName() + ":acquire lock again！");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    //获得几次可重入锁，这里就要释放几次
                    mutex.release();
                    System.out.println(Thread.currentThread().getName() + ":release lock！");
                    mutex.release();
                    System.out.println(Thread.currentThread().getName() + ":release lock again！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                mutex.acquire();
                System.out.println(Thread.currentThread().getName() + ":acquire lock！");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mutex.release();
                    System.out.println(Thread.currentThread().getName() + ":release lock！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        //看最下面注释
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //主线程获取释放锁
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + ":acquire lock！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
                System.out.println(Thread.currentThread().getName() + ":release lock！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 按照目前的代码跑的话，如果线程2获得锁，阻塞线程1和main线程，那么当线程2释放锁之后，
         * 如果接下来获得锁的是main线程，那么在main线程继续往下走的时候，线程1才获得锁，但是
         * 线程1中有两个模拟的耗时操作，正在执行，但是main线程已经往下走了，执行了client.close();
         * 已经把客户端关闭了，所以线程1中再获取锁的时候就会异常
         * （某次异常情况如下，每次执行不一定出异常，异常也不一定完全相同）：
         *
         * java.io.IOException: Lost connection while trying to acquire lock: /curator/lock
         * 	at org.apache.curator.framework.recipes.locks.InterProcessMutex.acquire(InterProcessMutex.java:91)
         * 	at cn.tang.base.web.DistributedLockTest.lambda$zookeeperLockTest$0(DistributedLockTest.java:33)
         * 	at java.lang.Thread.run(Thread.java:745)
         * java.lang.IllegalMonitorStateException: You do not own the lock: /curator/lock
         * 	at org.apache.curator.framework.recipes.locks.InterProcessMutex.release(InterProcessMutex.java:140)
         * 	at cn.tang.base.web.DistributedLockTest.lambda$zookeeperLockTest$0(DistributedLockTest.java:54)
         * 	at java.lang.Thread.run(Thread.java:745)
         *
         *  如果把下一行注释掉，每次运行都没问题(但是这样不行，用完就要关闭)，
         *  或者
         *  thread1.join();
         *  thread2.join();
         *  让两个子线程先执行。
         *  或者
         *  使用CountDownLatch在子线程中countDown，主线程中await，这样子线程都执行完，主线程的await才能继续往下走。
         */
        client.close();
    }


    /**
     * 使用redis实现分布式锁，借助新版本redis原子语句，及lua脚本实现。
     * （基于Redis单实例，未使用redis集群，若使用集群，参考Redission实现锁）
     * <p>
     * 分布式锁本质上要实现目标就是在Redis里面占一个坑，当别的进程也要来占用时，
     * 发现已经有人蹲在那里，只好放弃或者等待。
     * （等待最长时间，必须小于锁的过期时间。否则，假设锁2秒过期自动释放，但是A还没处理完（即：A的处理时间大于2秒），
     * 这时锁会因为redis key过期“提前”误释放，B重试时拿到锁，造成A,B同时处理。）
     * （以下分析基于Redis单实例）
     * <p>
     * 解决的问题：
     * （后进放弃）1.重复插入数据
     * （后进放弃）2.重复请求拦截
     * （后进等待）3.分布式系统共享资源使用（唯一订单号递增，获取到锁才能拿订单号）
     * ……待发现
     */

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    @Test
    @SuppressWarnings("all")
    public void redisLockTest() {
        //个人aliyun地址
        Jedis jedis = new Jedis("47.94.224.238", 6379);
        //redis锁key
        String lockKey = "key";
        //超时时间120s
        int expireTime = 120000;

        /**
         * 启动线程，获取锁，模拟1s耗时操作后，释放锁
         */
        Thread thread0 = new Thread(() -> {
            try {
                //获取当前线程请求ID，唯一
                String requestId = UUID.randomUUID().toString();
                boolean t1lockSuccess = tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
                if (t1lockSuccess) {
                    log.info("\n[{}] lock result:[{}]，key=[{}],value=[{}],expireTime=[{}] ms", new Object[]{Thread.currentThread().getName(), t1lockSuccess, lockKey, requestId, expireTime});
                    System.out.println(Thread.currentThread().getName() + "获取到redis锁！");
                    //模拟耗时操作
                    Thread.sleep(1000);
                    boolean t0releaseLockSuccess = releaseDistributedLock(jedis, lockKey, requestId);
                    if (t0releaseLockSuccess) {
                        log.info("\n[{}] release lock result:[{}]，key=[{}],value=[{}]", new Object[]{Thread.currentThread().getName(), t0releaseLockSuccess, lockKey, requestId});
                        System.out.println(Thread.currentThread().getName() + "释放redis锁！");
                    } else {
                        System.err.println(Thread.currentThread().getName() + "释放redis锁失败！");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "未获取到redis锁！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /**
         * 启动线程，获取锁，一开始睡眠1s目的是先让线程0获取锁，模拟并发情况时，线程0获取到锁执行代码，
         * 这时另一线程也过来请求锁，此时因为线程0还在执行，未释放锁，所以请求锁是失败的。
         */
        Thread thread1 = new Thread(() -> {
            try {
                //让thread-0先获取到锁
                Thread.sleep(1000);
                String requestId = UUID.randomUUID().toString();
                boolean t2lockSuccess = tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
                if (t2lockSuccess) {
                    log.info("\n[{}] lock result:[{}]，key=[{}],value=[{}],expireTime=[{}] ms", new Object[]{Thread.currentThread().getName(), t2lockSuccess, lockKey, requestId, expireTime});
                    System.out.println(Thread.currentThread().getName() + "获取到redis锁！");
                    //模拟耗时操作
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    boolean t1releaseLockSuccess = releaseDistributedLock(jedis, lockKey, requestId);
                    if (t1releaseLockSuccess) {
                        log.info("\n[{}] release lock result:[{}]，key=[{}],value=[{}]", new Object[]{Thread.currentThread().getName(), t1releaseLockSuccess, lockKey, requestId});
                        System.out.println(Thread.currentThread().getName() + "释放redis锁！");
                    } else {
                        System.err.println(Thread.currentThread().getName() + "释放redis锁失败！");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "未获取到redis锁！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread0.start();
        thread1.start();

        try {
            thread0.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 等子线程获取锁执行完逻辑后，释放锁，此时主线程再获取锁，可以获取成功。
         */
        String requestId = UUID.randomUUID().toString();
        boolean lockSuccess = tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
        if (lockSuccess) {
            log.info("\n[{}] redis lock result:[{}]，key=[{}],value=[{}],expireTime=[{}] ms", new Object[]{Thread.currentThread().getName(), lockSuccess, lockKey, requestId, expireTime});
            System.out.println(Thread.currentThread().getName() + "获取到redis锁！");
            //模拟耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean releaseLockSuccess = releaseDistributedLock(jedis, lockKey, requestId);
            if (releaseLockSuccess) {
                log.info("\n[{}] redis release lock result:[{}]，key=[{}],value=[{}]", new Object[]{Thread.currentThread().getName(), lockSuccess, lockKey, requestId});
                System.out.println(Thread.currentThread().getName() + "释放redis锁！");
            } else {
                System.err.println(Thread.currentThread().getName() + "释放redis锁失败！");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "未获取到redis锁！");
        }
    }


    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
