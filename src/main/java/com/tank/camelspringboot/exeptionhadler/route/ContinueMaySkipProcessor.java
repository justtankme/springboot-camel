package com.tank.camelspringboot.exeptionhadler.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ContinueMaySkipProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			String body = exchange.getIn().getBody(String.class);
			System.out.println("continue not skip body " +  body);
			exchange.getIn().setBody("after exception continue not skip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
