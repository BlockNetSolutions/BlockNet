package de.blocknet.cloud;


import de.blocknet.cloud.manager.master.Master;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    @Getter
    private static Logger logger;

    public static void main(String[] args) {
        logger = registerLogger("Master");
        Master master = new Master(logger);
    }

    private static Logger registerLogger(String loggerName) throws SecurityException, IllegalArgumentException {
        Objects.requireNonNull(loggerName, "Logger name must not be null");
        if (loggerName.isEmpty()) {
            throw new IllegalArgumentException("Logger name must not be empty");
        }

        Logger logger = Logger.getLogger(loggerName);
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
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
        logger.addHandler(handler);
        return logger;
    }
}

