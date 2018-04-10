package com.tank.camelspringboot.beanmethod;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class Log3 extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("direct:log3")
        .to("log:log3?showAll=true&multiline=true");
    }
}