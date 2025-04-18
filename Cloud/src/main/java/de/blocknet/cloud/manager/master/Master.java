package de.blocknet.cloud.manager.master;

import de.blocknet.api.storage.json.ConfigManager;
import de.blocknet.api.storage.mysql.impl.MasterConfig;
import de.blocknet.api.storage.mysql.impl.MySQLConfig;
import de.blocknet.cloud.Main;
import de.blocknet.cloud.http.server.HttpServerFrontend;
import de.blocknet.cloud.manager.command.CommandManager;
import de.blocknet.cloud.manager.master.command.ClearCommand;
import de.blocknet.cloud.manager.master.command.HelpCommand;
import de.blocknet.cloud.manager.master.command.HttpServerCommand;
import de.blocknet.cloud.manager.master.command.StopCommand;
import de.blocknet.cloud.terminal.CommandCompleter;
import de.blocknet.cloud.terminal.Extra;
import de.blocknet.cloud.utils.MessageStyler;
import lombok.Getter;
import lombok.SneakyThrows;
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
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Master {


    private String USER = System.getProperty("user.name");

    private Logger logger;

    private String VERSION = Main.class.getPackage().getImplementationVersion();

    @Getter
    public static Master instance;

    @Getter
    private HttpServerFrontend frontendServer;

    @SneakyThrows
    public Master(Logger logger) {
        instance = this;
        this.logger = logger;
        new CommandManager().init();
        CommandManager.getInstance().registerCommand(new HelpCommand());
        CommandManager.getInstance().registerCommand(new ClearCommand());
        CommandManager.getInstance().registerCommand(new StopCommand());
        CommandManager.getInstance().registerCommand(new HttpServerCommand());
        ConfigManager configManager = new ConfigManager();
        configManager.addModule(new MasterConfig(new MySQLConfig("test",1,"t","ddd","sdfdfsafds")), true);

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

        Extra.clear(terminal, false);
        this.frontendServer = new HttpServerFrontend(8000, "/web");
        frontendServer.start();
        Main.getLogger().info(("The cloud will launch shortly"));
        Extra.clear(terminal, true);
        if (Objects.equals(System.getProperty("user.name"), "root")) {
            Main.getLogger().warning(MessageStyler.getFormattedString("§3It is not recommended to start BlockNet as root user!"));
        }

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


                    if (!CommandManager.getInstance().runCommand(terminal, cmdName, lineArgs)) {
                        logger.warning(new AttributedString("", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi());
                    }
                }
            }
        } catch (UserInterruptException e) {
            /*terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();*/
            // User hit Ctrl-C or Ctrl-D
            stop();
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


    public void stop() {
        Main.getLogger().info(MessageStyler.getFormattedString("§3Stopping all services..."));
        this.frontendServer.stop();
        Main.getLogger().info(MessageStyler.getFormattedString("§1Stopping..."));
        System.exit(0);
    }
}
