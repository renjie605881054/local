package spring.dynamicProxyCglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("before---" + methodProxy.getSuperName());
		System.out.println(method.getName());
		Object result = methodProxy.invokeSuper(object, objects);
		System.out.println("after---" + methodProxy.getSuperName());
		return result;
	}

}
