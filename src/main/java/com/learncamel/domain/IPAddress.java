package com.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord( separator = "\\|",skipFirstLine=true ,generateHeaderColumns = true)
public class IPAddress {
    public String getDiscoveryIP() {
        return discoveryIP;
    }

    public void setDiscoveryIP(String discoveryIP) {
        this.discoveryIP = discoveryIP;
    }

    public String getIdragIP() {
        return idragIP;
    }

    public void setIdragIP(String idragIP) {
        this.idragIP = idragIP;
    }

    public String getSelfServiceIP() {
        return selfServiceIP;
    }

    public void setSelfServiceIP(String selfServiceIP) {
        this.selfServiceIP = selfServiceIP;
    }

    @Override
    public String toString() {
        return "{" +
                "\"discoveryIP\" : \"" + discoveryIP + '\"' +
                ", \"idragIP\" : \"" + idragIP + '\"' +
                ", \"selfServiceIP\" : \"" + selfServiceIP + '\"' +
                '}';
    }

    @DataField(pos = 1, trim = true)
    private String discoveryIP;

    @DataField(pos = 2, trim = true)
    private String idragIP;

    @DataField(pos = 3, trim = true)
    private String selfServiceIP;

}
