package spring.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class DynamicProxy implements InvocationHandler{
	
	private Object object;//委托类对象
	
	public DynamicProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] objects) {
		Object result = null;
		try {
			System.out.println("before");
			result = method.invoke(object, objects);
			System.out.println("after");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
}
