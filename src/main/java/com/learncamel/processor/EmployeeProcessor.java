package com.learncamel.processor;

import org.apache.camel.Exchange;

public class EmployeeProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println(" Body : "+exchange.getIn().getBody());
    }
}
