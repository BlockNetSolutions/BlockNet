package de.blocknet.cloud.http.frontend.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.blocknet.cloud.Main;
import de.blocknet.cloud.http.utils.MimeType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrontendHandler implements HttpHandler {

    private String resourceDir;


    public FrontendHandler(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String root = resourceDir; // Root directory for resources in JAR
        String requestURI = t.getRequestURI().toString();
        if (requestURI.endsWith("/")) {
            requestURI += "index.html";
        }else if(requestURI.equalsIgnoreCase("/stop")){
            System.out.println(t.getRemoteAddress().getAddress().getHostAddress());
            t.getResponseHeaders().set("Location", "/");
            t.sendResponseHeaders(301, -1);
            t.close();
        }


        String resourcePath = root + requestURI;
        InputStream resource = Main.class.getResourceAsStream(resourcePath);

        if (resource == null) {
            String path404 = root + "/index.html";
            InputStream page404 =  Main.class.getResourceAsStream(path404);
            // Resource not found
            /*String response = "404 (Not Found)\n";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();*/

            byte[] buffer = new byte[8192];
            int count;
            t.getResponseHeaders().set("Content-Type", getMimeType(path404) + "; charset=utf-8");
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            while ((count = page404.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
            os.close();
            return;
        }

        // Resource found, serve it
        byte[] buffer = new byte[8192];
        int count;
        t.getResponseHeaders().set("Content-Type", getMimeType(resourcePath) + "; charset=utf-8");
        t.sendResponseHeaders(200, 0);
        OutputStream os = t.getResponseBody();
        while ((count = resource.read(buffer)) > 0) {
            os.write(buffer, 0, count);
        }
        os.close();
    }

    private String getMimeType(String filePath) {
        // Use the Java 7 Files.probeContentType method to determine the MIME type
        Path path = Paths.get(filePath);
        try {
            String mimeType = Files.probeContentType(path);
            if (mimeType == null) {
                // If Files.probeContentType returns null, try to determine the MIME type based on the file extension
                String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
                mimeType = MimeType.get(extension);
            }
            return mimeType;
        } catch (IOException e) {
            // If an I/O error occurs, return a default MIME type
            return "application/octet-stream";
        }
    }
}
