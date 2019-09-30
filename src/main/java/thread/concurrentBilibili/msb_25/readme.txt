总结：
1.对于map/set的选择使用（map和set本质上是一样的东西，只不过map包含key、value；而set只包含key）
不加锁：
HashMap
TreeMap
LinkedHashMap

加锁并发性不高：
HashTable（用的很少了）
Collections.synchronizedXXX（并发不是很高的情况下使用，跟HashTable性能差不多）

加锁并发性很高：
ConcurrentHashMap
ConcurrentSkipListMap（并发高且排序）


2.对于list的选择使用(队列)
ArrayList
LinkedList
Collections.synchronizedXXX
CopyOnWriteList(写少读多)

在高并发的情况下，可以使用两种队列
(Queue、BlockingQueue、TransferQueue为接口；剩下的都是实现类，实现类都继承AbstractQueue抽象类)
Queue
        ConcurrentLinkedQueue（内部加锁）
    BlockingQueue（阻塞队列（满了等待，空了等待））
        LinkedBlockingQueue（无界队列，用的很多）
        ArrayBlockingQueue（有界队列，队列中元素数是一定的；到了线程池中，里面一个个放的就是任务）
        SynchronousQueue（一种特殊的TransferQueue，容量为0）
        DelayQueue（执行定时任务）
    TransferQueue（消息直接给消费者消费）
        LinkedTransferQueue
