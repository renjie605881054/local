package multi_thread;

public class MyThread extends Thread{
	
	public MyThread(){} 
	
	public MyThread(String name){
		this.setName(name);
	}
	
	int count = 5;
	@Override
	public synchronized void run() {
		super.run();
		System.out.println(currentThread().getName() + ",count:" + count--);
	}
	
	
}
