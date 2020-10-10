//package com.linp.study_mq.rabbit;
//
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import java.io.IOException;
//
///**
// * @Author : linpeng
// * ON 2020-10-09
// * used for:
// */
//public class ConnectUtil {
//
//
//    public static Connection getConnect() throws IOException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("111.231.82.55");//个人服务器地址
//        connectionFactory.setVirtualHost("my_vhost");//启动Mq时设置的虚拟机名称
//        connectionFactory.setUsername("admin");//启动Mq时设置的账号密码
//        connectionFactory.setPassword("admin");//启动Mq时设置的账号密码
//        Connection connection = connectionFactory.newConnection();
//        return connection;
//    }
//}
