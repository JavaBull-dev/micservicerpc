package cn.ljpc.framework;

import java.io.Serializable;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 10:27
 */
public class URL implements Serializable {

    private String hostname;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
