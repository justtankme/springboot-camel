package multicastandwiretap;

import java.time.Instant;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EndProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			System.out.println("end processor 1");
			TimeDTO time = exchange.getIn().getBody(TimeDTO.class);
			time.setEndInstant(Instant.now());
			exchange.getIn().setBody(time, TimeDTO.class);
			System.out.println("end processor 2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
