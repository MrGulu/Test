package thread.lockAndcondition;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndConditionTest {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    @Test
    public void test1() {

    }
}
