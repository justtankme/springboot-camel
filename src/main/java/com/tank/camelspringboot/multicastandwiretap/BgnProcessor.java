package com.tank.camelspringboot.multicastandwiretap;

import java.time.Instant;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BgnProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			TimeDTO time = exchange.getIn().getBody(TimeDTO.class);
			if (time == null) {
				time = new TimeDTO();
			}
			time.setBgnInstant(Instant.now());
			exchange.getIn().setBody(time, TimeDTO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
