package multicastandwiretap;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class DoNothingThread extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
		from("direct:donothing")
        .process(new EndProcessor())
        .to("log:donothing?showAll=true&multiline=true");
    }
}