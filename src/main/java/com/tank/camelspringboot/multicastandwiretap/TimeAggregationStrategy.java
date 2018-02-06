package com.tank.camelspringboot.multicastandwiretap;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class TimeAggregationStrategy implements AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
//		oldExchange.getIn(type)
		return null;
	}

}
