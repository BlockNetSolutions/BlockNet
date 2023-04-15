package de.blocknet.cloud.terminal;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;

public class CommandCompleter implements Completer {
    private final List<String> commands;

    public CommandCompleter(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        String word = line.word();
        for (String command : commands) {
            if (command.startsWith(word)) {
                candidates.add(new Candidate(command));
            }
        }
    }
}
