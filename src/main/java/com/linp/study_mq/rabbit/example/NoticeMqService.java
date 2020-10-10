package com.linp.study_mq.rabbit.example;//package com.ishanshan.message.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 接受消息
 */
@Service
public class NoticeMqService {

	private static final Logger log = LoggerFactory.getLogger(NoticeMqService.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 接受消息
	 * @param msg
	 */
	public Object sendMessage(String msg){

		MessageProperties messageProperties =  new MessageProperties();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", msg);
		messageProperties.setExpiration("30000");
		Message message = new Message(	ObjectToByte(map), messageProperties);
		rabbitTemplate.send(RabbitMQConfiguration.ROUTING_KEY, message);
		return "success";

	}

	public  byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}
}
