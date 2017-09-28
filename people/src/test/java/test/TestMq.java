package test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.util.SerializationUtils;

import com.people.pojo.User;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public class TestMq {

	final static String QNAME = "people-queues";
	final static String USERNAME = "renjie";
	final static String PASSWORD = "18686251078";
	final static int PORT = 5672;
	final static String HOST = "47.91.236.230";
	
	static Channel channel = null;
	static Connection connection = null;
	
	public static void main(String[] args) {
		consumer();
		//provider();
	}
	public static void provider(){
		
		try {
			//����һ��Ƶ��  
			channel = getConnectionMq().createChannel();  
			//ָ��һ������  
			channel.queueDeclare(QNAME, false, false, false, null);
			 //���͵���Ϣ  
	        String message = "hello world!";  
	        
	        for(int i = 0; i < 100; i++){
	        	User user = new User("С����" + i);
	        	//�������з���һ����Ϣ  
	        	channel.basicPublish("", QNAME, null, SerializationUtils.serialize(user));
	        }
	        System.out.println(" [x] Sent '" + message + "'");  
	       
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}finally{
			 //�ر�Ƶ��������  
	        try {
				channel.close();  
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}  
		}
	}
	public static void consumer(){
		//����һ��Ƶ��  
		try {
			channel = getConnectionMq().createChannel();
			 //����Ҫ��ע�Ķ���
	        channel.queueDeclare(QNAME, false, false, false, null);
	        System.out.println("Customer Waiting Received messages");
	      
	        /* ���������߶������ڶ�ȡ��Ϣ */  
	        @SuppressWarnings("deprecation")
			QueueingConsumer consumer = new QueueingConsumer(channel);  
	        channel.basicConsume(QNAME, true, consumer);  
	  
	        /* ��ȡ���У��������������ڶ�����Ϣ֮ǰ������������ֱ���ȵ���Ϣ�������Ϣ���Ķ��󣬼�������ѭ�� */  
	        while (true) {  
	            @SuppressWarnings("deprecation")
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
	            User user=(User)SerializationUtils.deserialize(delivery.getBody());  
	            System.out.println("�յ���Ϣ'" + user.toString() + "'");  
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (ShutdownSignalException e) {
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  finally{
			 //�ر�Ƶ��������  
	        try {
				channel.close();  
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}  
		}
		
	}
	
	public static Connection getConnectionMq() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);;
		factory.setPort(PORT);
		return factory.newConnection();
	}

}
