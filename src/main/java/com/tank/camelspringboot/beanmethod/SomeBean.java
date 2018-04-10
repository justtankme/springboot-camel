package com.tank.camelspringboot.beanmethod;

import org.apache.camel.Exchange;

public class SomeBean {
	public boolean someMethod(Exchange exchange) {
		System.out.println("调用someMethod bean方法了");
		return false;
	}
	public boolean anotherMethod(Exchange exchange) {
		System.out.println("调用anotherMethod bean方法了");
		return false;
	}
}
