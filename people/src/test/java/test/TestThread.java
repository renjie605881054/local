package test;

public class TestThread extends Thread{

	private String threadName;
	
	public TestThread(String name){
		this.threadName = name;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i< 100; i++)
				System.out.println(threadName + "- running...");
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestThread t1 = new TestThread("t1");
		TestThread t2 = new TestThread("t2");
		
		t1.start();
		t2.start();
	}

}
