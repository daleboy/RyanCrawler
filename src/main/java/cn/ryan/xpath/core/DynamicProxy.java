package cn.ryan.xpath.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DynamicProxy implements InvocationHandler {
	private Object proxy;

	public DynamicProxy(Object proxy) {
		this.proxy = proxy;
	}

	public DynamicProxy() {
	}

	public Object bind(Object proxy) {
		this.proxy = proxy;
		return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), proxy.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(this.proxy, args);
	}

	public Object invoke(Method method, Element e) throws Throwable {
		return method.invoke(this.proxy, e);
	}

	public Object invoke(Method method, Elements es) throws Throwable {
		return method.invoke(this.proxy, es);
	}

	public static void main(String[] args) {
		DynamicProxy dp = new DynamicProxy(new AxisSelectorExpand());
		try {
			Method method = Class.forName("cn.ryan.xpath.core.AxisSelectorExpand").getMethod("to");
			System.out.println(dp.invoke(null, method, null));
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
