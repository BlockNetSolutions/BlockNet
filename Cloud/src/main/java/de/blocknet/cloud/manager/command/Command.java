package de.blocknet.cloud.manager.command;

import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

public interface Command {


    String getName();


    String[] getAliases();

    HashMap<Integer, ArrayList<String>> getArguments();

    void onCommand(Terminal terminal, String[] args);

    String getHelpInfo();

}
