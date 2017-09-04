

public class TestRunnable implements Runnable{

	private Thread t;
	private String threadName;
	private int count = 5;
	
	public TestRunnable(){};
	
	public TestRunnable(String ThreadName){
		this.threadName = ThreadName;
	};
	
	public synchronized void run() {
		try {
			for(int i = 0; i < 10; i++){
				//System.out.println(threadName + "-当前运行：" + Thread.currentThread());
				if(count>0){
					System.out.println(threadName + "-当前运行：" + Thread.currentThread() + count--);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addCount(int value){
		this.count = this.count - value; 
	}
	
	public void start(){
		if(t == null){
			t = new Thread(this);
			t.setName(threadName);
			t.start();
		}
	}

	
	public static void main(String[] args) {
		
		/*TestRunnable runnable1 = new TestRunnable("runnable1");
		TestRunnable runnable2 = new TestRunnable("runnable2");
		TestRunnable runnable3 = new TestRunnable("runnable3");
		
		runnable1.start();
		runnable2.start();
		runnable3.start();*/
		
		TestRunnable runnable = new TestRunnable();
		new Thread(runnable, "111").start();
		new Thread(runnable, "222").start();
		new Thread(runnable, "333").start();
	}
}
