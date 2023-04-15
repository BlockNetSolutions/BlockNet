package de.blocknet.cloud;


import java.io.IOException;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static String USER = System.getProperty("user.name");
    private static Logger LOGGER = null;

    private static String VERSION = Main.class.getPackage().getImplementationVersion();


    static {
        Logger mainLogger = Logger.getLogger("de.blocknet");
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            //private static final String format = "[%1$tF %1$tT] %2$-7s: %3$s %n";
            private static final String format = "[%1$tT] %2$-7s: %3$s %n";
            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        mainLogger.addHandler(handler);
        LOGGER = Logger.getLogger(Main.class.getName());
    }


    public static void main(String[] args) throws IOException, InterruptedException {


    }



    public static Logger getLogger(){
        return LOGGER;
    }
}

