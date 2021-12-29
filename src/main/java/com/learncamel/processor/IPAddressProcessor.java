package com.learncamel.processor;

import com.learncamel.domain.IPMapper;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class IPAddressProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        List<?> list = new ArrayList<>();
        Object valueArray = exchange.getIn().getBody();
        if (valueArray.getClass().isArray()) {
            list = Arrays.asList((Object[])valueArray);
        } else if (valueArray instanceof Collection) {
            list = new ArrayList((Collection<?>)valueArray);
        }

        System.out.println(" Body : "+exchange.getIn().getBody());
    }


}