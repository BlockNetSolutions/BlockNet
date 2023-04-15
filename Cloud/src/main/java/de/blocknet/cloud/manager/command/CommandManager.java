package de.blocknet.cloud.manager.command;

import lombok.Getter;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {


    @Getter
    private static CommandManager instance;

    @Getter
    public List<Command> commands = new ArrayList<Command>();

    public void init() {
        instance = this;
    }


    public void registerCommand(Command command){
        getCommands().add(command);
    }

    public List<String> getCommandsAsName(){
        List<String> commands = new ArrayList<String>();

        for(Command command : getCommands()){
            commands.add(command.getName());
        }


        return commands;
    }



    public Command getCommand(String name){
        Command command = null;
        for (Command c : getCommands()) {

            if (c.getName().equalsIgnoreCase(name)) {
                command = c;
                break;
            }
        }

        return command;
    }

    public boolean runCommand(Terminal terminal, String name, String[] args) {
        Command command = null;
        for (Command c : getCommands()) {

            if (c.getName().equalsIgnoreCase(name)) {
                command = c;
                break;
            }
        }

        if (command != null) {
            command.onCommand(terminal, args);
            return true;
        } else {
            return false;
        }
    }
}
