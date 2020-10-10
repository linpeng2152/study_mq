//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.QueueingConsumer;
//
///**
// * @Author : linpeng
// * ON 2020-10-10
// * used for: 定
// *
// *     <dependency>
// *             <groupId>com.rabbitmq</groupId>
// *             <artifactId>amqp-client</artifactId>
// *             <version>3.4.1</version>
// *         </dependency>
// *
// */
//public class TestSubscribeReceive {
//
//    public static void main(String[] args) throws Exception{
//        //通过rabbitmq连接工具类得到连接
//        Connection connection=ConnectUtil.getConnect();
//        //创建通道
//        Channel channel = connection.createChannel();
//        /*
//         * 创建交换机exchange
//         * 第一参数：交换机名称
//         * 第二参数：交换机类型：
//         *
//         */
//        channel.exchangeDeclare("test_fanout", "fanout");
//        //创建队列
//        channel.queueDeclare("test_fanout_queue1", false, false, false, null);
//        /*
//         * 绑定队列到交换机
//         * 第一参数：队列名称
//         * 第二参数：交换机名称
//         * 第三参数：
//         *
//         */
//        channel.queueBind("test_fanout_queue1", "test_fanout", "");
//        // 同一时刻服务器只会发一条消息给消费者
//        channel.basicQos(1);
//        //定义消费者
//        QueueingConsumer queueingConsumer=new QueueingConsumer(channel);
//        //监听队列
//        channel.basicConsume("test_fanout_queue1", false,queueingConsumer);
//        while(true) {
//            //获取消息
//            QueueingConsumer.Delivery nextDelivery = queueingConsumer.nextDelivery();
//            String message=new String(nextDelivery.getBody());
//            System.out.println(message);
//            Thread.sleep(10);
//            //手动回复完成
//            channel.basicAck(nextDelivery.getEnvelope().getDeliveryTag(), false);
//
//        }
//
//    }
//
//
//
//}
