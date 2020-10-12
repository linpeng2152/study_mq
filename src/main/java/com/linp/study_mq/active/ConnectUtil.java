package com.linp.study_mq.active;


import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
public class ConnectUtil {

    public static Connection getConnection() throws JMSException {

        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://111.231.82.55:61616");
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();

        return connection;
    }

}
