package com.linp.study_mq.active.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
@Service
public class ItemMessageListener  {

    @JmsListener(destination = "itemAddTopic",containerFactory = "jmsQueueListenerContainerFactory")
    public void itemAddTopic(String text) {
//        try {
//            String text = ((TextMessage)message).getText();
            System.out.println( "接收到消息 " + text);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }
}
