package ActiveMQ.withSpring.test;

import ActiveMQ.withSpring.queueName.QueueName;
import ActiveMQ.withSpring.sender.QueueSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:activeMq.xml")
public class ProducerTest {

    @Autowired
    private QueueSender queueSender;

    @Test
    public void testSendMessage() {
        for (int i = 0; i < 5; i++) {
            queueSender.send(QueueName.TEST_QUEUE, "爱宝宝，哈哈哈！");
        }
        Map map = new HashMap();
        map.put("name", "xiaodugulu");
        queueSender.send(QueueName.TEST_QUEUE,map);
    }
}
