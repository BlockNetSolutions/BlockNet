package de.blocknet.cloud.command;

import de.blocknet.cloud.terminal.Extra;
import org.jline.terminal.Terminal;

public class ClearCommand implements Command {

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    @Override
    public void onCommand(Terminal terminal, String[] args) {
        Extra.clear(terminal);
    }

    @Override
    public String getHelpInfo() {
        return "clears the Terminal";
    }


}
