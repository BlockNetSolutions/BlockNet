package de.blocknet.cloud.manager.master;

import de.blocknet.cloud.Main;
import de.blocknet.cloud.manager.command.CommandManager;
import de.blocknet.cloud.manager.master.command.ClearCommand;
import de.blocknet.cloud.manager.master.command.HelpCommand;
import de.blocknet.cloud.terminal.CommandCompleter;
import de.blocknet.cloud.terminal.Extra;
import lombok.Getter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Master {


    private String USER = System.getProperty("user.name");

    @Getter
    private Logger logger;
    private String VERSION = Main.class.getPackage().getImplementationVersion();

    @Getter
    private String name = "Master";

    @Getter
    public Master instance;

    public Master() {
        registerLogger();
        instance = this;

        new CommandManager().init();
        CommandManager.getInstance().registerCommand(new HelpCommand());
        CommandManager.getInstance().registerCommand(new ClearCommand());


        TerminalBuilder builder = TerminalBuilder.builder();
        Terminal terminal = null;
        try {
            terminal = builder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .completer(new CommandCompleter(CommandManager.getInstance().getCommandsAsName()))
                .build();

        Extra.clear(terminal);




        String prompt = new AttributedString(USER, AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "@BlockNet-" + VERSION
                + new AttributedString(" # ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi();

        String line;
        try {
            while ((line = reader.readLine(prompt)) != null) {
                if (!line.isEmpty()) {
                    line = line.trim();
                    line = line.toLowerCase();
                    String[] lineArgs = line.split(" ");
                    String cmdName = lineArgs[0];
                    List<String> lineArgsTemp = new ArrayList<>(Arrays.asList(lineArgs));
                    lineArgsTemp.remove(0);
                    lineArgs = lineArgsTemp.toArray(new String[lineArgsTemp.size()]);


                    if(!CommandManager.getInstance().runCommand(terminal, cmdName, lineArgs)){
                        logger.warning(new AttributedString("Command \"" + cmdName + "\" not found!", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi());
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
            try {
                terminal.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void registerLogger() {
        Logger mainLogger = Logger.getLogger("de.blocknet.cloud.master");
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

        this.logger = mainLogger;
    }

}
