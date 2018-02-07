package com.tank.camelspringboot.exeptionhadler.route;

import java.net.SocketTimeoutException;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimeOutRoute extends RouteBuilder{
//	@Autowired
//	private CamelContext camelContext;

	@Override
	public void configure() throws Exception {
		//		Endpoint endpoint = new ServletEndpoint("/timeout", null, );
//		endpoint.
//				;EndpointHelper.;
//		HttpComponent component = new HttpComponent();
//		component.setCamelContext(camelContext);
//		HttpClientConnectionManager clientConnectionManager = new BasicHttpClientConnectionManager();
//		Endpoint endpoint = new HttpEndpoint("/timeouthttp", component, 
//				new URI("http4://localhost:8080/timeout2?bridgeEndpoint=true&throwExceptionOnFailure=false"), clientConnectionManager);
		
//		ErrorHandlerBuilder errorHandlerBuilder = new DefaultErrorHandlerBuilder();
//		errorHandlerBuilder.
//		errorHandler(errorHandlerBuilder);
//		Processor onExceptionOccurred;
		onException(SocketTimeoutException.class).handled(true).process(new SocketTimeoutProcessor());
		from("servlet://timeout")
        .to("log:beforehttp?showAll=true&multiline=true")
        .setHeader("CamelHttpMethod", constant("GET"))
		.to("http4://localhost:8080/timeout2?bridgeEndpoint=true&throwExceptionOnFailure=false&httpClient.socketTimeout=5000")
        .convertBodyTo(String.class, "utf-8")
        .to("log:afterhttp?showAll=true&multiline=true")
//        .onException(SocketTimeoutException.class)
//        .setOnExceptionOccurred(onExceptionOccurred);
//        .to(endpoint);
        ;
	}

}
