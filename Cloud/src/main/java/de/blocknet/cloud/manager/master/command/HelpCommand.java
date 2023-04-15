package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.manager.command.CommandManager;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStyle;

import java.util.Arrays;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }


    @Override
    public String[] getAliases() {
        return new String[]{
                "h",
        };
    }

    @Override
    public void onCommand(Terminal terminal, String[] args) {
        if (args.length < 1) {
            for (Command command : CommandManager.getInstance().getCommands()) {
                printHelp(command);
            }
        } else {
            if (CommandManager.getInstance().getCommand(args[0]) != null) {
                Command command = CommandManager.getInstance().getCommand(args[0]);
                printHelp(command);
            } else {
                MessageStyler.sendInfo("This command does not exist!", AttributedStyle.DEFAULT, AttributedStyle.RED);
            }
        }
    }

    public void printHelp(Command command) {
        if(command.getAliases().length != 0){
            MessageStyler.sendInfo(command.getName() + "\t->\t" + command.getHelpInfo() + "\t-> Aliases: " + Arrays.toString(command.getAliases()), AttributedStyle.DEFAULT, AttributedStyle.CYAN);
        }else{
            MessageStyler.sendInfo(command.getName() + "\t->\t" + command.getHelpInfo(), AttributedStyle.DEFAULT, AttributedStyle.CYAN);
        }
    }

    @Override
    public String getHelpInfo() {
        return "Shows the help info";
    }


}
