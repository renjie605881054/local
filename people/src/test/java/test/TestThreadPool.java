package test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestThreadPool {

	public static void main(String[] args) {
		//newCachedPool();
		//newFixedPool();
		//newScheduledPool();
		newSinglePool();
	}
	
	/**
	 *  newCachedThreadPool����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
	 */
	public static void newCachedPool(){
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			for(int i=0;i<100;i++){
				final int index = i;
				Thread.sleep(50);
				pool.execute(new Runnable() {
					public void run() {
						System.out.println(index);
					}
				});
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * newFixedThreadPool ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
	 */
	public static void newFixedPool(){
		try {
			ExecutorService pool = Executors.newFixedThreadPool(3);
			for(int i=0;i<10;i++){
				final int index = i;
				Thread.sleep(50);
				pool.execute(new Runnable() {
					public void run() {
						System.out.println(index);
					}
				});
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * newScheduledThreadPool ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
	 */
	public static void newScheduledPool(){
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		/*pool.schedule(new Runnable() {
			public void run() {
				System.out.println("delay 3 seconds...");
			}
		}, 3, TimeUnit.SECONDS);*/
		pool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay ...");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}
	
	/**
	 * newSingleThreadExecutor ����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ�С�
	 */
	public static void newSinglePool(){
		try {
			ExecutorService pool = Executors.newSingleThreadExecutor();
			for(int i=0;i<50;i++){
				final int index = i;
				pool.execute(new Runnable() {
					public void run() {
						System.out.println(index);
					}
				});
				Thread.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
