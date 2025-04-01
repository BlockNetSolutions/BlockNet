package de.blocknet.cloud.http.server.monitor;

import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.Setter;

import java.net.Inet4Address;
import java.net.InetSocketAddress;

public class Status {

    @Getter
    private Boolean isRunning;
    @Getter
    private InetSocketAddress inetAddress;
    @Getter
    private String host;
    @Getter
    private Integer port;

    public Status(HttpServer server, Boolean running){
        this.isRunning = running;
        this.port = server.getAddress().getPort();
        this.inetAddress = server.getAddress();
        this.host = "0.0.0.0";
    }
}
