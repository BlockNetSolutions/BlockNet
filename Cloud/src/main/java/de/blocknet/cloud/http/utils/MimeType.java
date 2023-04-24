package de.blocknet.cloud.http.utils;

import java.util.HashMap;
import java.util.Map;

public class MimeType {
    private static final Map<String, String> mimeTypes;

    static {
        mimeTypes = new HashMap<>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("js", "application/javascript");
        mimeTypes.put("json", "application/json");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("jpg", "image/jpg");
        mimeTypes.put("jpeg", "image/jpeg");
        mimeTypes.put("gif", "image/gif");
        mimeTypes.put("ico", "image/x-icon");
    }

    public static String get(String extension) {
        return mimeTypes.getOrDefault(extension, "application/octet-stream");
    }
}
