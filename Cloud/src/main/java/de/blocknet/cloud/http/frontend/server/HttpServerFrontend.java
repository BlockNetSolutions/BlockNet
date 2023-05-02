package de.blocknet.cloud.http.frontend.server;

import com.sun.net.httpserver.HttpServer;
import de.blocknet.cloud.http.frontend.handler.FrontendHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerFrontend {

    private int port;
    private String resourceDir;
    private Thread serverThread;

    private HttpServer server;

    public HttpServerFrontend(int port, String resourceDir) {
        this.resourceDir = resourceDir;
        this.port = port;
    }



    @SneakyThrows
    public void start() {
        stop();

        this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
        this.server.createContext("/", new FrontendHandler(this.resourceDir));
        this.server.start();
    }


    public void stop() {
        if (this.server != null) {
            this.server.stop(0);
        }
    }

}

