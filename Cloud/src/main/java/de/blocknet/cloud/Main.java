package de.blocknet.cloud;

import de.blocknet.setup.SetupManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Main {

    private static SetupManager setupManager;
    private static boolean isSetup;

    public static void main(String[] args) throws IOException {
        setup();

        Terminal terminal = TerminalBuilder.terminal();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String line;
        while ((line = lineReader.readLine("BlockNet > ")) != null) {
            System.out.println("Du hast eingetippt: " + line);
        }
    }

    private static void setup() {
        isSetup = true;
        if(isSetup) {
            setupManager = new SetupManager(true);
        }
    }
}

