package com.learncamel.processor;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.learncamel.domain.IPMapper;
import org.apache.camel.Exchange;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class IPAddressProcessor implements org.apache.camel.Processor {

    public void process(Exchange exchange) {
        List<IPMapper> data = (List<IPMapper>) exchange.getIn().getBody();
        Map<String, List<String>> ipByHost;
        ipByHost = data.stream().collect(Collectors.groupingBy(IPMapper::getHostName, Collectors.mapping((IPMapper p) -> p.getIp(), toList())));
        exchange.getIn().setBody(toCsvStream(ipByHost));
    }

    public Map<String, String> toCsvStream(Map<String, List<String>> mapToConvert) {
        try {
            Map<String, String> ipByHost = new HashMap<>();
            for (String key : mapToConvert.keySet()) {
                for (String s : mapToConvert.get(key)) {
                    Iterable<String> ips = Splitter.on(CharMatcher.anyOf(";,")).split(s);
                    for (String ip: ips) {
                        ipByHost.put(ip.trim(), key);
                    }
                }
            }
            return ipByHost;
        } catch (Exception e) {
            System.out.println("Exception Occured " + e.getMessage());
            return null;
        }
    }
}
