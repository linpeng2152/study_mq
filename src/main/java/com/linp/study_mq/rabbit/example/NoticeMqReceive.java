package com.linp.study_mq.rabbit.example;//package com.ishanshan.message.mq;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.linp.study_mq.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 队列消息的处理者
 * 同步
 */
@Component
@RabbitListener(queues = "sync")
public class NoticeMqReceive {

	private static final Logger log = LoggerFactory.getLogger(NoticeMqReceive.class);



	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * MQ 队列消息的处理者
	 */
	@RabbitListener(queues = RabbitMQConfiguration.MQ_QUEUE_NOTICE_NAME)
	public void receiveQueue(Message msg ) {
		Map<String, Object> map = (Map<String, Object>) ByteToObject(msg.getBody());
		String msgData = (String) map.get("msg");
		/**
		 * 队列的消息 实际处理
		 */

		System.out.println("接收到消息 ------------------- ");
		System.out.println(JacksonUtils.serializeObjectToJson(msgData));
	}

	 public  Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
	        return obj;
	    }
}
