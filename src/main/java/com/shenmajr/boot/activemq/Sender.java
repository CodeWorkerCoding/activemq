package com.shenmajr.boot.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by fujianjian on 2016/7/20.
 */
public class Sender {
    private static final int SENDER_NUMBER = 5;

    public static void main(String[] args){
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;

        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:10086");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, producer);
            session.commit();


        } catch (JMSException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        for (int i=1; i<= SENDER_NUMBER; i++){
            TextMessage message = session.createTextMessage("发送的消息"+i);
            System.out.println("发送消息："+"MQ 发送的消息" + i);
            producer.send(message);
        }
    }
}

