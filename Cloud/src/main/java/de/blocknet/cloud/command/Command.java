package de.blocknet.cloud.command;

import org.jline.terminal.Terminal;

public interface Command {


    String getName();

    String[] getArgs();

    void onCommand(Terminal terminal, String[] args);

    String getHelpInfo();

}
