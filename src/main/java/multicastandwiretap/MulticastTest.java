package multicastandwiretap;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MulticastTest extends RouteBuilder {

    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("servlet://multicasttest")
		.process(new BgnProcessor())
		.multicast()
//		.aggregate(aggregationStrategy)
		.parallelProcessing()
        .to("direct:longthread","direct:quickthread")
        .end()
        .to("direct:donothing")
        .convertBodyTo(String.class, "utf-8");
    }
}