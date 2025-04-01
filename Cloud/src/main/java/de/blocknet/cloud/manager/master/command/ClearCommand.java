package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.terminal.Extra;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

public class ClearCommand implements Command {

    @Override
    public String getName() {
        return "clear";
    }


    @Override
    public String[] getAliases() {
        return new String[]{
                "cls",
        };
    }

    @Override
    public HashMap<Integer, ArrayList<String>> getArguments() {
        return null;
    }


    @Override
    public void onCommand(Terminal terminal, String[] args) {
        Extra.clear(terminal, true);
    }

    @Override
    public String getHelpInfo() {
        return "clears the Terminal";
    }


}
