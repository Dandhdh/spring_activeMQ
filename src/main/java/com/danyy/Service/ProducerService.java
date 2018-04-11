package com.danyy.Service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ProducerService {

    @Resource(name = "jmsTopicTemplate")
    private  JmsTemplate jmsTopicTemplate;

    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination,final String msg){
        System.out.println(Thread.currentThread().getName()+" �����"+destination.toString()+"������Ϣ---------------------->"+msg);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public void sendMessage(final String msg){
        String destination = jmsTemplate.getDefaultDestinationName();
        System.out.println(Thread.currentThread().getName()+" �����"+destination+"������Ϣ---------------------->"+msg);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public void sendTopicMessage(Destination destination, final String msg){
        if(null == destination){
            destination = jmsTopicTemplate.getDefaultDestination();
        }
        System.out.println(Thread.currentThread().getName()+" ������"+destination+"������Ϣ---------------------->"+msg);
        jmsTopicTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
        System.out.println("spring send text message...");
    }
}