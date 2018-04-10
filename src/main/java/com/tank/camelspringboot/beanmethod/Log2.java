package com.tank.camelspringboot.beanmethod;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class Log2 extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("direct:log2")
        .to("log:log2?showAll=true&multiline=true");
    }
}