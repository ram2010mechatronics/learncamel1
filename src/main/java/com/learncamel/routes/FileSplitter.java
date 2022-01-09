package com.learncamel.routes;

import com.learncamel.domain.IPMapper;
import com.learncamel.processor.IPAddressProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;

public class FileSplitter {
    public static void main(String[] args) throws Exception {

        DataFormat bindy = new BindyCsvDataFormat(IPMapper.class);
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:data/csv/input?fileName=TestFile.csv&noop=true")
                        .unmarshal(bindy)
                        .to("log:?level=INFO&showBody=true&showHeaders=true")
                        .process(new IPAddressProcessor())
                        .to("file:data/csv/input?fileName=OutputFile.csv");
            }
        });
        context.start();
        Thread.sleep(5000);
        context.stop();
        System.out.println("End");
    }
}
