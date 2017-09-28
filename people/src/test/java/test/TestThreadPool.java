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
	 *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
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
	 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
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
	 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
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
	 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
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
