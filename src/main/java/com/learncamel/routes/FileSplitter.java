package com.learncamel.routes;

import com.learncamel.domain.IPAddress;
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
        DataFormat ip = new BindyCsvDataFormat(IPAddress.class);
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {

                from("file:data/csv/input?fileName=SourceFile.csv&noop=true")
                        .unmarshal(bindy)
                        .process(new IPAddressProcessor())
                        .to("direct:source_record");

                from("file:data/csv/input?fileName=InputFile.csv&noop=true")
                        .unmarshal(ip)
                        .pollEnrich("direct:source_record",new RecordsAggregationStrategy())
                        .to("file:data/csv/output?fileName=OutputFile.csv");

            }
        });
        context.start();
        System.out.println("Started Processing");
        Thread.sleep(5000);
        context.stop();
        System.out.println("Completed...");
    }
}
