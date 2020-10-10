//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//
///**
// * @Author : linpeng
// * ON 2020-10-09
// * used for:
// */
//public class TestOne2MoreSend {
//
//    private static final String QUEUE_NAME = "test_simple_queue";
//
//
//    public static void main(String[] args) throws Exception {
//        //获取连接和通道
//        Connection connection = ConnectUtil.getConnect();
//        Channel channel = connection.createChannel();
//        //声明队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        String message = "";
//        for (int i = 0; i < 100; i++) {
//            message = "" + i;
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            System.out.println("发送消息：" + message);
//            Thread.sleep(i);
//        }
//
//        channel.close();
//        connection.close();
//    }
//}
