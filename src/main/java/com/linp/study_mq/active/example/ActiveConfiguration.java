package com.linp.study_mq.active.example;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Queue;

/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
@Configuration
public class ActiveConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://111.231.82.55:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }


//
//    @Bean
//    public JmsListenerContainerFactory jmsListenerContainerFactory() {
//        JmsListenerContainerFactory jmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
//        return jmsListenerContainerFactory;
//    }

    @Bean
    public JmsTemplate genJmsTemplate() {
        return new JmsTemplate(connectionFactory());

    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    @Bean(name = "itemAddTopic")
    public Destination destination() {
        Destination destination = new ActiveMQTopic("itemAddTopic");
        return destination;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {

        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //设置连接数
        factory.setConcurrency("3-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(true);

        return factory;

    }

}
