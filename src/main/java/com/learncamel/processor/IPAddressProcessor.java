package com.learncamel.processor;

import com.learncamel.domain.IPAddress;
import com.learncamel.domain.IPMapper;
import org.apache.camel.Exchange;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class IPAddressProcessor implements org.apache.camel.Processor {
    List<Character> forbidden= new ArrayList<>();

    public void process(Exchange exchange) throws Exception {
        List<IPMapper> data = (List<IPMapper>) exchange.getIn().getBody();
        Map<String, List<IPAddress>> ipByHost;
        ipByHost = data.stream().collect(Collectors.groupingBy(IPMapper::getHostName, Collectors.mapping((IPMapper p) -> {
            return p.getIpAddress();
        }, toList())));
        exchange.getIn().setBody(toCsvStream(ipByHost));
    }

    public String toCsvStream(Map<String, List<IPAddress>> mapToConvert) {
        try {
            forbidden.add(';');
            forbidden.add(',');
            StringBuilder output = new StringBuilder();
            for (String key : mapToConvert.keySet()) {
                output.append(key);
                output.append(ToIPStream(mapToConvert.get(key)));
                output.append("\n");
            }
            return output.toString();
        } catch (Exception e) {
            System.out.println("Exception Occured " + e.getMessage());
            return null;
        }
    }
    public String ToIPStream(List<IPAddress> listToConvert){

        StringBuilder output = new StringBuilder();
        for (IPAddress ips : listToConvert) {
            if (!ips.getDiscoveryIP().isEmpty()) {
                output.append("|");
                output.append(RemoveChars(ips.getDiscoveryIP()));
            }
            if (!ips.getIdragIP().isEmpty()) {
                output.append("|");
                output.append(RemoveChars(ips.getIdragIP()));
            }
            if (!ips.getSelfServiceIP().isEmpty()) {
                output.append("|");
                output.append(RemoveChars(ips.getSelfServiceIP()));
            }
        }
        return output.toString();
    }

    public String RemoveChars(String input)
    {
        return input
                .replaceAll(
                        forbidden.stream()
                                .map(String::valueOf)
                                .map(Pattern::quote)
                                .collect(Collectors.joining("|")),
                        "|");
    }

}
