package com.tank.camelspringboot.multicastandwiretap;

import java.time.Instant;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class LongThread extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		Processor processor = new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println(Thread.currentThread().getName() + " before thread sleep");
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName() + " after thread sleep");
				TimeDTO timeDTO = exchange.getIn().getBody(TimeDTO.class);
				timeDTO.setEndInstant(Instant.now());
				exchange.getIn().setBody(timeDTO, TimeDTO.class);
			}
		};
        from("direct:longthread")
        .process(processor)
        .to("log:longthread?showAll=true&multiline=true");
    }
}