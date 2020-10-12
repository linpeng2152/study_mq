package com.linp.study_mq.active;


import javax.jms.*;
import java.io.IOException;

/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
public class TestOne2OneConsumer {


    public static void main(String[] args) {
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
            MessageConsumer consumer = session.createConsumer(queue);
            //7、向consumer对象中设置一个messageListener对象，用来接收消息
            consumer.setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    // TODO Auto-generated method stub
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage)message;
                        try {
                            System.out.println(textMessage.getText());
                        } catch (JMSException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });
            //8、程序等待接收用户消息
            System.in.read();
            //9、关闭资源
            consumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
