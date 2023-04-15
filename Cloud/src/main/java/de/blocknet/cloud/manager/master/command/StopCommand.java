package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStyle;

public class StopCommand implements Command {

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String[] getAliases() {
        return new String[]{
                "exit",
        };
    }


    @Override
    public void onCommand(Terminal terminal, String[] args) {
        System.out.println(MessageStyler.getColoredAnsi("Stopping...", AttributedStyle.DEFAULT, AttributedStyle.RED));
        System.exit(0);
    }

    @Override
    public String getHelpInfo() {
        return "Stops the Cloud";
    }


}
