package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.manager.master.Master;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<Integer, ArrayList<String>> getArguments() {
        HashMap<Integer, ArrayList<String>> args = new HashMap<Integer, ArrayList<String>>();
        args.put(0, new ArrayList<String>());

        args.get(0).add("-force");

        return args;
    }


    @Override
    public void onCommand(Terminal terminal, String[] args) {
        Master.getInstance().stop();
    }

    @Override
    public String getHelpInfo() {
        return "Stops the Cloud";
    }


}
