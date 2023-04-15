package de.blocknet.cloud.commands;

import de.blocknet.cloud.Main;
import de.blocknet.cloud.terminal.Extra;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

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
