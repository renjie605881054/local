package spring.dynamicProxy;

import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;

public class Start {

	public static void main(String[] args) {
		Vender v = new Vender();
		DynamicProxy proxy = new DynamicProxy(v);
		Sell sell = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[] { Sell.class }, proxy);
		sell.sell();
		
	}

}
