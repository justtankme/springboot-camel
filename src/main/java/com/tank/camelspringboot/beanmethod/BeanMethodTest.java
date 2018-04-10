package com.tank.camelspringboot.beanmethod;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.bean.BeanLanguage;
import org.springframework.stereotype.Component;

@Component
public class BeanMethodTest extends RouteBuilder {

    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("servlet://beanmethodtest")
		.choice()
			.when().expression(BeanLanguage.bean(SomeBean.class, "someMethod"))
	        .to("direct:log1")
	        .when().method(SomeBean.class, "anotherMethod")
	        .to("direct:log2")
			.otherwise()
	        .to("direct:log3")
		.endChoice();
    }
}