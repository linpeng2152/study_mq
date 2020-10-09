package com.linp.study_mq.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @Author : linpeng
 * ON 2020-10-09
 * used for:
 */
public class ConnectUtil {

    public static Connection getConnect() throws IOException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("111.231.82.55");
        connectionFactory.setVirtualHost("my_vhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
