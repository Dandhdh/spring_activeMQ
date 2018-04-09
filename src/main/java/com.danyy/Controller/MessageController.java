package com.danyy.Controller;

import com.danyy.Service.ConsumerService;
import com.danyy.Service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/1/5.
 */
@Controller
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    //消息队列
    @Resource(name = "demoQueueDestination")
    private Destination destination;

    //消息主题
    @Resource(name = "demoTopicDestination")
    private Destination demoTopicDestination;

    //队列消息生产者
    @Resource(name = "producerService")
    private ProducerService producer;

    //队列消息消费者
    @Resource(name = "consumerService")
    private ConsumerService consumer;

    @RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void send(String msg) {
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        for(int i=0;i<100; i++){
            producer.sendMessage(msg);
        }
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
    }

    @RequestMapping(value= "/ReceiveMessage",method = RequestMethod.GET)
    @ResponseBody
    public Object receive(){
        logger.info(Thread.currentThread().getName()+"------------receive from jms Start");
        TextMessage tm = consumer.receive(destination);
        logger.info(Thread.currentThread().getName()+"------------receive from jms End");
        return tm;
    }

    @RequestMapping(value = "/SendTopicMessage", method = RequestMethod.POST)
    @ResponseBody
    public void sendTopicMessage(String msg) {
        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
        producer.sendTopicMessage(null,msg);
        logger.info(Thread.currentThread().getName()+"------------send to jms End");
    }

}