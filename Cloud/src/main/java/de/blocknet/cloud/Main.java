package de.blocknet.cloud;

import de.blocknet.setup.SetupManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;


import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static Logger LOGGER = null;

    static {
        Logger mainLogger = Logger.getLogger("de.blocknet");
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

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





    public static void main(String[] args) throws IOException {

        TerminalBuilder builder = TerminalBuilder.builder();
        Terminal terminal = builder.build();
        String prompt = "BlockNet -> ";
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String line;
        try {
            while ((line = reader.readLine("BlockNet -> ")) != null) {
                if (!line.isEmpty()) {
                    System.out.println("Du hast eingegeben: " + line);
                }
            }
        } catch (UserInterruptException e) {
            // User hit Ctrl-C or Ctrl-D
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            terminal.close();
        }
    }
}

