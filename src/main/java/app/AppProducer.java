package app;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * ��Ϣ������
 */
public class AppProducer {

    //ActiveMq ��Ĭ���û���
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq ��Ĭ�ϵ�¼����
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ �����ӵ�ַ
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;


    // ������Ϣ������
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        // ���ӹ���
        ConnectionFactory connectionFactory;
        // ����
        Connection connection = null;
        // �Ự ���ͻ��߽�����Ϣ���߳�
        Session session;
        // ��Ϣ��Ŀ�ĵ�
        Destination destination;
        // ��Ϣ������
        MessageProducer messageProducer;
        // ʵ�������ӹ���
        connectionFactory = new ActiveMQConnectionFactory(AppProducer.USERNAME, AppProducer.PASSWORD,
                AppProducer.BROKEURL);

        try {
            // ͨ�����ӹ�����ȡ����
            connection = connectionFactory.createConnection();
            // ��������
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // ������Ϣ����
            destination = session.createTopic("danYY��s Topic");
            // ������Ϣ������
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer);
            // ���������������������Ҫʹ���ύ���ܽ����ݷ��ͳ�ȥ
            session.commit();

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // ������Ϣ
    public static void sendMessage(Session session, MessageProducer messageProducer) {
        for (int i = 0; i < AppProducer.SENDNUM; i++) {
            try {
                TextMessage message = session.createTextMessage("Active MQ������Ϣ" + i);
                System.out.println("������Ϣ��Active MQ������Ϣ");
                messageProducer.send(message);
            } catch (JMSException e) {
                // TODO Auto-generated catc h block
                e.printStackTrace();
            }
        }
    }
}
