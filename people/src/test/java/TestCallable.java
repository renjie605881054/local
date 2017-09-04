import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class TestCallable implements Callable<String>{


	public static void main(String[] args) {
		try {
			TestCallable call = new TestCallable();
			FutureTask<String> future = new FutureTask<String>(call);
			FutureTask<String> future2 = new FutureTask<String>(call);
			new Thread(future).start();
			new Thread(future2).start();
			System.out.println("get :" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String call() throws Exception {
		int i = 0;
		for(i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName() + "running:" + i);
			Thread.sleep(50);
		}
		return "×îÖÕ·µ»Ø:" + i;
	}

}
