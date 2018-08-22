package ActiveMQ.withSpring.consumer;

import ActiveMQ.withSpring.sender.QueueSender;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component("queueConsumer")
public class QueueConsumer implements MessageListener {

    @Autowired
    QueueSender queueSender;

    @Override
    public void onMessage(Message message) {
        ObjectMessage obj = (ObjectMessage) message;
        try {
            System.out.println(obj.getObject().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
//                if (message instanceof ActiveMQTextMessage) {
//            ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
////            mqDelegate.execute(params, amqMessage.getText());
//            try {
//                System.out.println(amqMessage.getText());
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        } else {
//            BytesMessage bm = (BytesMessage) message;
//            byte data[] = new byte[0];
//            try {
//                data = new byte[(int) bm.getBodyLength()];
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//            try {
//                bm.readBytes(data);
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
////            mqDelegate.execute(params, new String(data));
//            System.out.println(new String(data));
//        }
    }
}
