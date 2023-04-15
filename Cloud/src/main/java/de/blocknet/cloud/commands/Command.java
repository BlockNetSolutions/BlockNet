package de.blocknet.cloud.commands;

import jdk.nashorn.internal.ir.Terminal;

public interface Command {


    void onCommand(String name, String[] args);

    String getHelpInfo();

}
