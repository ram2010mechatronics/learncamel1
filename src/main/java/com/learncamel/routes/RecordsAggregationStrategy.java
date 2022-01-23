package com.learncamel.routes;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.learncamel.domain.IPAddress;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import java.util.List;
import java.util.Map;

public class RecordsAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }
        List<IPAddress> oldBody = (List<IPAddress>) oldExchange.getIn().getBody();
        Map<String, String> newBody = (Map<String, String>) newExchange.getIn().getBody();
        oldExchange.getIn().setBody(ConvertToString(oldBody,newBody));
        return oldExchange;
    }

    public String ConvertToString(List<IPAddress> list, Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        for (IPAddress ip : list) {
            sb.append(ip.getIp());
            sb.append("|");
            Iterable<String> ips;
            ips = Splitter.on(CharMatcher.anyOf(";,")).split(ip.getIp());
            for (String item: ips) {
                sb.append(",");
                sb.append(map.get(item.trim()));
            }
            sb.append("\n");
        }
        return sb.toString().replace("|,","|");
    }
}
