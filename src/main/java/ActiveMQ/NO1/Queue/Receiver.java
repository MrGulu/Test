package ActiveMQ.NO1.Queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {

    public static void main(String[] args) {

        //connectionFactory 连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory;

        //connection JMS客户端到JMS provider 的连接
        Connection connection = null;

        //session一个发送或者接收的线程
        Session session;

        //destination 消息目的地，发送给谁接收
        Destination destination;

        //消费者消息接收者
        MessageConsumer consumer;

        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        try {
            //构造工厂得到连接对象
            connection = connectionFactory.createConnection();

            //启动
            connection.start();

            //获取操作连接
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);

            while(true){
                //设置接收者收消息的时间，为了方便测试，这里暂定设置为100s
                TextMessage message = (TextMessage)consumer.receive(100);
                if(null != message){
                    System.out.println("收到消息==="+message.getText());
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != connection){
                    connection.close();
                }
            } catch (Throwable ignore) {
            }
        }
    }
}
