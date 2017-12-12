package spring.dynamicProxy;

public class Vender implements Sell {

	@Override
	public void sell() {
		System.out.println("vender - sell");
	}

	@Override
	public void ad() {

	}

}
