package spring.dynamicProxy;

public class BusinessAgent implements Sell {

	private Vender vender;
	
	public BusinessAgent(Vender vender) {
		this.vender = vender;
	}
	
	@Override
	public void sell() {
		vender.sell();
	}

	@Override
	public void ad() {
		System.out.println("before");
	}

}
