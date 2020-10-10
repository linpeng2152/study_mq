//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.Consumer;
//import com.rabbitmq.client.QueueingConsumer;
//
///**
// * @Author : linpeng
// * ON 2020-10-09
// * used for:
// *
// *        对应写法的版本
//          <dependency>
// *             <groupId>com.rabbitmq</groupId>
// *             <artifactId>amqp-client</artifactId>
// *             <version>3.4.1</version>
// *         </dependency>
// *
// */
//public class TestOne2MoreConsumer1 {
//
//    private static final String QUEUE_NAME = "test_simple_queue";
//
//
//    //消费者1
//    public static void main(String[] args) throws Exception {
//        Connection connection = ConnectUtil.getConnect();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        //同一时刻服务器只发送一条消息给消费端
//        channel.basicQos(1);
//
//        Consumer consumer = new Consumer(channel);
//        channel.basicConsume(QUEUE_NAME,false,consumer);
//        while(true){
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("recive1:"+message);
//            Thread.sleep(100);
//            //消息消费完给服务器返回确认状态，表示该消息已被消费
//            //multiple 设置为 true 会自动把小于当前的 都一次性确认
//            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
//        }
//    }
//}
