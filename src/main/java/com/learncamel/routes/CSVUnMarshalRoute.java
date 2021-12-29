package com.learncamel.routes;

import com.learncamel.domain.Employee;
import com.learncamel.processor.EmployeeProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;

public class CSVUnMarshalRoute {

  public static void main(String[] args) throws Exception {

    DataFormat bindy = new BindyCsvDataFormat(Employee.class);
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() {
        from("file:data/csv/input?fileName=file4.txt&noop=true")
                .unmarshal(bindy)
                .to("log:?level=INFO&showBody=true&showHeaders=true")
                .process(new EmployeeProcessor())
                .to("direct:output");
      }
    });
    context.start();
    Thread.sleep(5000);
    context.stop();
    System.out.println("End");
  }
}

