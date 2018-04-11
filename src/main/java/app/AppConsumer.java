package app;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ��Ϣ������
 */
public class AppConsumer {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// Ĭ�ϵ����ӵ��û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// Ĭ�ϵ���������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// Ĭ�ϵ����ӵ�ַ

	public static void main(String[] args) {
		// ���ӹ���
		ConnectionFactory connectionFactory;
		// ����
		Connection connection = null;
		// �Ự ���ܻ��߷�����Ϣ���߳�
		Session session;
		// ��Ϣ��Ŀ�ĵ�
		Destination destination;
		// ��Ϣ��������
		MessageConsumer messageConsumer;
		

		connectionFactory = new ActiveMQConnectionFactory(AppConsumer.USERNAME, AppConsumer.PASSWORD,
				AppConsumer.BROKEURL);
		try {
			// ͨ�����ӹ�����ȡ����
			connection=connectionFactory.createConnection();
			// ��������
			connection.start();
			// �����ỰSession
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic("danYY��s Topic");
			// ������Ϣ������
			messageConsumer=session.createConsumer(destination);
			// ע����Ϣ����
			messageConsumer.setMessageListener(new Listener());
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
