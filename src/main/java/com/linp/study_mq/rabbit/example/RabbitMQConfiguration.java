package com.linp.study_mq.rabbit.example;//package com.ishanshan.message.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableRabbit
public class RabbitMQConfiguration implements RabbitListenerConfigurer {


	public static final String ROUTING_KEY = "some-routing-key";

	public static final String MQ_EXCHANGE_NAME = "saas-exchange";

	public static final String MQ_QUEUE_NOTICE_NAME = "saas.queue.erp.wxnotice";

	@Bean
	RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	TopicExchange exchange(RabbitAdmin rabbitAdmin) {
		TopicExchange topicExchange = new TopicExchange(MQ_EXCHANGE_NAME);
		rabbitAdmin.declareExchange(topicExchange);
		return topicExchange;
	}


	/**
	 * 通知消息队列
	 * @param rabbitAdmin
	 * @return
	 */
	@Bean
	Queue queueNotice(RabbitAdmin rabbitAdmin) {
		Map<String, Object> args = new HashMap<String,Object>();
		args.put("x-message-ttl", 365*24*60*60*1000);
		args.put("x-dead-letter-exchange", MQ_EXCHANGE_NAME);
		args.put("x-dead-letter-routing-key", "some-routing-key");
		Queue queue = new Queue(MQ_QUEUE_NOTICE_NAME, true,false,false,args);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

	@Bean
	Binding bindingExchangeNoticeExecuteMessage(Queue queueNoticeExecute,
			TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder
				.bind(queueNoticeExecute)
				.to(exchange)
				.with(ROUTING_KEY);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setExchange(MQ_EXCHANGE_NAME);
		rabbitTemplate.setDefaultReceiveQueue(MQ_QUEUE_NOTICE_NAME);
		return rabbitTemplate;
	}


	@Bean
	public RabbitMessagingTemplate rabbitMessagingTemplate(
			RabbitTemplate rabbitTemplate) {
		RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
		rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
		rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
		return rabbitMessagingTemplate;
	}

	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

	@Bean
	public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(jackson2Converter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(
			RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
	}

}
