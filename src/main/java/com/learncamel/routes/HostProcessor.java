package com.learncamel.routes;

import org.apache.camel.Exchange;

public class HostProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) {
    System.out.println(exchange.getIn().getBody().toString());
    }
}
