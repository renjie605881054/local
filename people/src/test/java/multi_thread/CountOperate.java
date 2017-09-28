package multi_thread;

public class CountOperate extends Thread{

	public CountOperate(){
		System.out.println("constr begin:" + currentThread().getName() + "," + getName() + " ,end");
	};

	@Override
	public void run() {
		System.out.println("run begin:" + currentThread().getName() + "," + currentThread().isAlive() + " ,end");
		System.out.println("run begin-this:" + this.getName() + "," + this.isAlive() + " ,end");
		System.out.println("isAlive£º" + this.isAlive());
	}
}
