package de.blocknet.cloud.terminal;

import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.manager.command.CommandManager;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.*;

public class CommandCompleter implements Completer {
    private final List<String> commands;

    public CommandCompleter(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public void complete(LineReader reader, ParsedLine parsedLine, List<Candidate> candidates) {

        String orgLine = parsedLine.line();
        String line = orgLine;

        line = line.trim();
        line = line.toLowerCase();
        String[] lineArgs = line.split(" ");
        String cmdName = lineArgs[0];
        List<String> lineArgsTemp = new ArrayList<>(Arrays.asList(lineArgs));
        lineArgsTemp.remove(0);
        lineArgs = lineArgsTemp.toArray(new String[lineArgsTemp.size()]);

        int argIndex;
        if (orgLine.endsWith(" ")) {
            argIndex = lineArgs.length;
        }else{
            argIndex = lineArgs.length - 1;
        }

        if(CommandManager.getInstance().getCommand(cmdName) != null){
            Command command = CommandManager.getInstance().getCommand(cmdName);

            if(command.getArguments().containsKey(argIndex)) {

                for(String arg : command.getArguments().get(argIndex)) {
                    candidates.add(new Candidate(arg));
                }
            }

        }


        /*String word = parsedLine.word();
        for (String command : commands) {
            if (command.startsWith(word)) {
                candidates.add(new Candidate(command));
            }
        }*/
    }
}
