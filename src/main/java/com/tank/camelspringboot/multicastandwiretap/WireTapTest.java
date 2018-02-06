package com.tank.camelspringboot.multicastandwiretap;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WireTapTest extends RouteBuilder {

    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("servlet://wiretaptest")
		.process(new BgnProcessor())
        .wireTap("direct:longthread")
        .to("direct:quickthread")
        .to("direct:donothing")
        //不加这行的话，如果使用exchange.getIn().setBody(time, TimeDTO.class)会导致stream close的错误
        .convertBodyTo(String.class, "utf-8");
    }
}