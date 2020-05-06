package thread.readWriteLock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tangwenlong
 * @description 读写锁-读多写少场景
 * @date 2020-04-15
 */
public class ReadWriteLockTest {

    @Test
    public void test1() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
    }
}
