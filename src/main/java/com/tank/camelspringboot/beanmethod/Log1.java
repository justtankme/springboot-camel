package com.tank.camelspringboot.beanmethod;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class Log1 extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("direct:log1")
        .to("log:log1?showAll=true&multiline=true");
    }
}