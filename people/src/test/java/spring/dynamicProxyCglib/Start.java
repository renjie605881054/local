package spring.dynamicProxyCglib;

import org.springframework.cglib.proxy.Enhancer;

import spring.dynamicProxy.Sell;
import spring.dynamicProxy.Vender;

public class Start {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Vender.class);
		enhancer.setCallback(proxy);
		
		Sell sell = (Sell)enhancer.create();
		sell.sell();
	}

}
