package de.blocknet.cloud.commands;

import org.jline.terminal.Terminal;

import java.util.List;

public interface Command {


    String getName();

    String[] getArgs();

    void onCommand(Terminal terminal, String[] args);

    String getHelpInfo();

}
