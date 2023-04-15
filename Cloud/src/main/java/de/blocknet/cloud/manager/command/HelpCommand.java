package de.blocknet.cloud.manager.command;

import de.blocknet.cloud.Main;
import de.blocknet.cloud.command.Command;
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
        if(args.length < 1){
            for(Command command : CommandManager.getInstance().getCommands()){
                printHelp(command);
            }
        }else{
            if(CommandManager.getInstance().getCommand(args[0]) != null){
                Command command = CommandManager.getInstance().getCommand(args[0]);
                printHelp(command);
            }else{
                Main.getLogger().warning(new AttributedString("This command does not exist!", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi());
            }
        }
    }

    public void printHelp(Command command){
        Main.getLogger().info(new AttributedString(command.getName() + "\t->\t" + command.getHelpInfo(), AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN)).toAnsi());
    }

    @Override
    public String getHelpInfo() {
        return "Shows the help info";
    }


}
