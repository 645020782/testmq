package com.utstar.testmq.admin;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection conn = null;
		Session session = null;
		Destination dest = null;
		MessageConsumer mc = null;
		factory = new ActiveMQConnectionFactory("tcp://192.168.1.172:61616");
		try {
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			dest = session.createQueue("HelloWorld");
			mc = session.createConsumer(dest);
			while (true) {
				TextMessage text = (TextMessage) mc.receive(1000000L);
				if (text != null) {
					System.out.println("收到的消息:" + text.getText());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
