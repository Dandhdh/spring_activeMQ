package com.danyy.Filter;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.*;

/**
 * Created by Administrator on 2017/1/5.
 */
public class QueueMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener���������ı���Ϣ��\t"
                    + tm.getText());
            //do something ...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
