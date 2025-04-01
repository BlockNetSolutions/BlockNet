package de.blocknet.cloud.http.server;

import com.sun.net.httpserver.HttpServer;
import de.blocknet.cloud.Main;
import de.blocknet.cloud.http.server.handler.HtmlHandler;
import de.blocknet.cloud.http.server.monitor.Status;
import de.blocknet.cloud.utils.MessageStyler;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

public class HttpServerFrontend {

    private int port;
    private String resourceDir;
    private Thread serverThread;
    @Getter
    private Status status;

    private HttpServer server;

    public HttpServerFrontend(int port, String resourceDir) {
        this.resourceDir = resourceDir;
        this.port = port;
        this.status = null;
    }



    @SneakyThrows
    public void start() {
        stop();
        Main.getLogger().info(MessageStyler.getFormattedString("§3Starting HTTP server on port " + port + "..."));

        this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
        this.server.createContext("/", new HtmlHandler(this.resourceDir));
        this.server.start();
        this.status = new Status(server, true);
        Main.getLogger().info(MessageStyler.getFormattedString("§2HTTP server started!"));
    }


    public void stop() {
        if (this.server != null) {
            Boolean realStop = false;
            if(this.status.getIsRunning()) {
                realStop = true;
                Main.getLogger().info(MessageStyler.getFormattedString("§3Stopping HTTP server..."));
            }
            this.server.stop(0);
            this.status = new Status(server, false);
            if (realStop) {
                Main.getLogger().info(MessageStyler.getFormattedString("§1HTTP server stopped!"));
            }
        }
    }

}

