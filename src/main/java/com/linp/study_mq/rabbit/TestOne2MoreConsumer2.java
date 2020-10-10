//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.QueueingConsumer;
//
///**
// * @Author : linpeng
// * ON 2020-10-09
// * used for:
// *     <dependency>
// *             <groupId>com.rabbitmq</groupId>
// *             <artifactId>amqp-client</artifactId>
// *             <version>3.4.1</version>
// *         </dependency>
// *
// */
//public class TestOne2MoreConsumer2 {
//
//    private static final String QUEUE_NAME = "test_simple_queue";
//
//    //生产者2
//    public static void main(String[] args) throws Exception {
//        Connection connection = ConnectUtil.getConnect();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        channel.basicQos(1);
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        /*
//         *  boolean autoAck ：是否自动签名 表示消息被消费
//         *  注意：服务需要返回成功消费 但是 autoAck设置为false 且没有手动的调用channel.basicAck
//         *  则该消费者会被认为处理中 不再投递
//         */
//        channel.basicConsume(QUEUE_NAME,true,consumer);
//        while(true){
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("recive1:"+message);
//            Thread.sleep(10);
////            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
//        }
//    }
//}
