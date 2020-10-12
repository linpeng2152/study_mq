package com.linp.study_mq.active;


import javax.jms.*;
/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
public class TestOne2OneProducer {

    public static void main(String[] args) {
        //连接工厂
        try {
            //连接
            Connection connection = ConnectUtil.getConnection();
            //启动连接
            connection.start();
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Queue queue = session.createQueue("test-queue");
            //6、使用会话对象创建生产者对象
            MessageProducer producer = session.createProducer(queue);
            for(int i =0;i<10;i++){
                //7、使用会话对象创建一个消息对象
                TextMessage textMessage = session.createTextMessage("发送消息 i = " + i);
                //8、发送消息
                producer.send(textMessage);
            }
            //9、关闭资源
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
