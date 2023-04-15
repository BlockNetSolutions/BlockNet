package de.blocknet.cloud;

import de.blocknet.cloud.terminal.CommandCompleter;
import de.blocknet.cloud.terminal.Extra;
import de.blocknet.setup.SetupManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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


    private static List<String> commands = Arrays.asList("clear", "help", "stop");


    public static void main(String[] args) throws IOException, InterruptedException {
        TerminalBuilder builder = TerminalBuilder.builder();
        Terminal terminal = builder.build();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .completer(new CommandCompleter(commands))
                .build();

        Extra.clear(terminal);




        String prompt = new AttributedString(USER, AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "@BlockNet-" + VERSION
                + new AttributedString(" ⚡︎ ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi();

        String line;
        try {
            while ((line = reader.readLine(prompt)) != null) {
                if (!line.isEmpty()) {
                    line = line.trim();
                    line = line.toLowerCase();
                    switch (line) {
                        case ("clear"):
                            Extra.clear(terminal);
                            break;
                        case ("help"):
                            System.out.println(" ");
                            Thread.sleep(2);
                            LOGGER.info(new AttributedString("clear\t->\tClear the Console", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi());
                            LOGGER.info(new AttributedString("help\t->\tShows this Message", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi());
                            LOGGER.info(new AttributedString("stop\t->\tStops the Cloud", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi());
                            System.out.println(" ");
                            break;
                        case ("stop"):
                            LOGGER.info(new AttributedString("Exiting...", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi());
                            System.exit(0);
                            break;
                        default:
                            if(line.split(" ")[0] != null && line.split(" ")[0].trim() != ""){
                                LOGGER.info(new AttributedString("Command \"" + line.split(" ")[0] + "\" not found", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi());
                            }
                            break;
                    }
                }
            }
        } catch (UserInterruptException e) {
            /*terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();*/
            // User hit Ctrl-C or Ctrl-D
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            terminal.close();
        }
    }

    public static Logger getLogger(){
        return LOGGER;
    }
}

