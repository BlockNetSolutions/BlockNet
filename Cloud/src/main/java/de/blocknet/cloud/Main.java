package de.blocknet.cloud;

import de.blocknet.setup.SetupManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.Console;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static SetupManager setupManager;
    private static boolean isSetup;


    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format("[%1$tH:%1$tM:%1$tS - %2$s] %3$s %n",
                        new java.util.Date(record.getMillis()),
                        record.getLevel().getName(),
                        record.getMessage()
                );
            }
        });

        logger.addHandler(handler);
        setup();


        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();





        String line;
        while ((line = lineReader.readLine("BlockNet > ")) != null) {
            logger.info("Du hast eingetippt: " + line);
        }
    }

    private static void setup() {
        isSetup = true;
        if(isSetup) {
            setupManager = new SetupManager(true);
        }
    }
}

