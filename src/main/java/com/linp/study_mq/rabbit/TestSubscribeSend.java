//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//
///**
// * @Author : linpeng
// * ON 2020-10-10
// * used for: 订阅模式
// */
//public class TestSubscribeSend {
//
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
//         */
//        channel.exchangeDeclare("test_fanout", "fanout");
//        //消息内容
//        String message="testFanout 测试消息";
//        /*
//         * 发送消息
//         * 第一参数：交换机名称
//         * 第二参数：
//         * 第三参数：
//         * 第四参数：消息（字节流）
//         *
//         */
//        channel.basicPublish("test_fanout", "", null, message.getBytes());
//        System.out.println("发送消息："+message);
//        //关闭资源
//        channel.close();
//        connection.close();
//    }
//}
