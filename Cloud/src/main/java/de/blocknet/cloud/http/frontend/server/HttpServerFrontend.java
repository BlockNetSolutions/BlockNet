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

    public HttpServerFrontend(int port, String resourceDir) {
        this.resourceDir = resourceDir;
        this.port = port;
    }



    @SneakyThrows
    public void start() {
        stop();

        new Thread(() -> {
            try {
                HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
                server.createContext("/", new FrontendHandler(this.resourceDir));
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void stop() {
        if(this.serverThread != null) {
            serverThread.stop();
        }
    }

}

