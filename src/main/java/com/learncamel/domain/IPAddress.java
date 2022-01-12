package com.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord( separator = "\\|",skipFirstLine=true ,generateHeaderColumns = true)
public class IPAddress {

    @DataField(pos = 1, trim = true)
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "{" +
                "\"IP\" : \"" + ip + '\"' +
                '}';
    }

}
