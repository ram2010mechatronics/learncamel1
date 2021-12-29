package com.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@CsvRecord( separator = "\\|",skipFirstLine=true ,generateHeaderColumns = true)
public class IPMapper {

    @Link
    private IPAddress ipAddress;

    @Override
    public String toString() {
        return "{" +
                "\"ipAddress\" : " + ipAddress +
                ", \"hostName\" : \"" + hostName + '\"' +
                '}';
    }

    @DataField(pos = 4)
    private String hostName;

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}
