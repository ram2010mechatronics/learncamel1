package com.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord( separator = "\\|",skipFirstLine=true ,generateHeaderColumns = true)
public class IPMapper {

    @DataField(pos = 2, trim = true)
    private String hostName;

    @DataField(pos = 1, trim = true)
    private String ip;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIP(String iP) {
        this.ip = iP;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ipAddress\" : " + ip +
                ", \"hostName\" : \"" + hostName + '\"' +
                '}';
    }

}
