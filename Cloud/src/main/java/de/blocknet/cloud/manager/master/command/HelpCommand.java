package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.manager.command.CommandManager;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }


    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public HashMap<Integer, ArrayList<String>> getArguments() {
        HashMap<Integer, ArrayList<String>> args = new HashMap<Integer, ArrayList<String>>();
        args.put(0, new ArrayList<String>());

        for (Command command : CommandManager.getInstance().getCommands()) {
            if (command != this) {
                args.get(0).add(command.getName());
            }
        }

        return args;
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
                MessageStyler.sendInfo("§1This command does not exist!");
            }
        }
    }

    public void printHelp(Command command) {
        if (command.getAliases().length != 0) {
            MessageStyler.sendInfo("§6" + command.getName() + "\t§7->\t§2" + command.getHelpInfo());
        } else {
            MessageStyler.sendInfo("§6" + command.getName() + "\t§7->\t§2" + command.getHelpInfo());
        }
    }

    @Override
    public String getHelpInfo() {
        return "Shows the help info";
    }


}
