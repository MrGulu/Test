package activeMQ.withSpring.sender;

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
/*在上边的代码中，我们在发送Object对象时，我们发送的对象需要实现serializable接口。
为什么一个类要实现serializable接口呢？
一个类只有实现了serializable才是可以序列化的，通俗的讲实现了serializable接口后我们将可以把这个类，
在网络上进行发送，或者将这个类存入到硬盘，序列化的目的就是保存一个对象。*/
