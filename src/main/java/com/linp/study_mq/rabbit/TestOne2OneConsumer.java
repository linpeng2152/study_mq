//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.QueueingConsumer;
//
//import java.io.IOException;
//
///**
// * @Author : linpeng
// * ON 2020-10-09
// * used for: 测试一对一
// *     <dependency>
// *             <groupId>com.rabbitmq</groupId>
// *             <artifactId>amqp-client</artifactId>
// *             <version>3.4.1</version>
// *         </dependency>
// *
// */
//public class TestOne2OneConsumer {
//
//    private static final String QUEUE_NAME = "test_simple_queue";
//
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        consumer();
//    }
//
//    //消息接收者
//    public static void consumer() throws IOException, InterruptedException {
//        //获取连接和通道
//        Connection connection = ConnectUtil.getConnect();
//        Channel channel = connection.createChannel();
//        //声明通道
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        //定义消费者
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        //监听队列
//        channel.basicConsume(QUEUE_NAME,true,consumer);
//
//        while(true){
//            //这个方法会阻塞住，直到获取到消息
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("接收到消息："+message);
//        }
//    }
//
//}
