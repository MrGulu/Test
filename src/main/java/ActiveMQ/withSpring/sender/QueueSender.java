package ActiveMQ.withSpring.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;


@Service
public class QueueSender {
	// 注入jmsTemplate
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	//第二个参数要用Object类型的，这样传什么参数都可以，之前是用的String类型的，会受限
	public void send(String queueName, final Object message) {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage mes = session.createObjectMessage((Serializable)message);
				return mes;
			}
		});
	}
}
