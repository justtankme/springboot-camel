package com.tank.camelspringboot.multicastandwiretap;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class QuickThread extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("direct:quickthread")
        .to("log:quickthread?showAll=true&multiline=true");
    }
}