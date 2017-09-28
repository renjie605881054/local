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
			//创建一个频道  
			channel = getConnectionMq().createChannel();  
			//指定一个队列  
			channel.queueDeclare(QNAME, false, false, false, null);
			 //发送的消息  
	        String message = "hello world!";  
	        
	        for(int i = 0; i < 100; i++){
	        	User user = new User("小明：" + i);
	        	//往队列中发出一条消息  
	        	channel.basicPublish("", QNAME, null, SerializationUtils.serialize(user));
	        }
	        System.out.println(" [x] Sent '" + message + "'");  
	       
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}finally{
			 //关闭频道和连接  
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
		//创建一个频道  
		try {
			channel = getConnectionMq().createChannel();
			 //声明要关注的队列
	        channel.queueDeclare(QNAME, false, false, false, null);
	        System.out.println("Customer Waiting Received messages");
	      
	        /* 创建消费者对象，用于读取消息 */  
	        @SuppressWarnings("deprecation")
			QueueingConsumer consumer = new QueueingConsumer(channel);  
	        channel.basicConsume(QNAME, true, consumer);  
	  
	        /* 读取队列，并且阻塞，即在读到消息之前在这里阻塞，直到等到消息，完成消息的阅读后，继续阻塞循环 */  
	        while (true) {  
	            @SuppressWarnings("deprecation")
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
	            User user=(User)SerializationUtils.deserialize(delivery.getBody());  
	            System.out.println("收到消息'" + user.toString() + "'");  
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
			 //关闭频道和连接  
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
