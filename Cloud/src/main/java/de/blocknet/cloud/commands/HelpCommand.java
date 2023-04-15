package de.blocknet.cloud.commands;

import de.blocknet.cloud.Main;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    @Override
    public void onCommand(Terminal terminal, String[] args) {
        for(Command command : CommandManager.getInstance().getCommands()){
            Main.getLogger().info(new AttributedString(command.getName() + "\t->\t" + command.getHelpInfo(), AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN)).toAnsi());
        }
    }

    @Override
    public String getHelpInfo() {
        return "Shows the help info";
    }


}
