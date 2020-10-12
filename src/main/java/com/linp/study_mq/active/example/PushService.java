package com.linp.study_mq.active.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
@Service
public class PushService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("itemAddTopic")
    private Destination destination;


    public void push(String content){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(content);
            }
        });
    }

}
