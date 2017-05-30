package com.utstar.testmq.admin;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Productor {
	//Ĭ�����ӵ�ַ
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //���͵���Ϣ����
    private static final int SENDNUM = 10;

	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection conn = null;
		Session session = null;
		Destination dest = null;
		MessageProducer mp = null;
		factory = new ActiveMQConnectionFactory("tcp://192.168.1.172:61616");
		try {
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			dest = session.createQueue("HelloWorld");
			mp = session.createProducer(dest);
			sendMessage(session, mp);
			session.commit();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void sendMessage(Session session, MessageProducer mp) throws JMSException {
		for (int i = 0; i < SENDNUM; i++) {
			TextMessage text = session.createTextMessage("ActiveMQ ������Ϣ" + i);
			System.out.println("������Ϣ��Activemq ������Ϣ" + i);
			mp.send(text);
		}
	}
}
