package de.blocknet.cloud.terminal;

import de.blocknet.cloud.Main;
import de.blocknet.cloud.manager.master.Master;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;

import java.util.Objects;

public class Extra {

    public static void clear(Terminal terminal, Boolean printSplash) {

        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        if (printSplash) {
            terminal.writer().println(
                    " \n" +
                            " \n" +
                            new AttributedString(" /$$$$$$$  /$$                     /$$       ", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "/$$   /$$             /$$    \n" +
                            new AttributedString("| $$__  $$| $$                    | $$      ", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$$ | $$            | $$    \n" +
                            new AttributedString("| $$  \\ $$| $$  /$$$$$$   /$$$$$$$| $$   /$$", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$$$| $$  /$$$$$$  /$$$$$$  \n" +
                            new AttributedString("| $$$$$$$ | $$ /$$__  $$ /$$_____/| $$  /$$/", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$ $$ $$ /$$__  $$|_  $$_/  \n" +
                            new AttributedString("| $$__  $$| $$| $$  \\ $$| $$      | $$$$$$/ ", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$  $$$$| $$$$$$$$  | $$    \n" +
                            new AttributedString("| $$  \\ $$| $$| $$  | $$| $$      | $$_  $$ ", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$\\  $$$| $$_____/  | $$ /$$\n" +
                            new AttributedString("| $$$$$$$/| $$|  $$$$$$/|  $$$$$$$| $$ \\  $$", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "| $$ \\  $$|  $$$$$$$  |  $$$$/\n" +
                            new AttributedString("|_______/ |__/ \\______/  \\_______/|__/  \\__/", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)).toAnsi() + "|__/  \\__/ \\_______/   \\___/     " + Main.class.getPackage().getImplementationVersion() + "\n" +
                            "                                                                          \n" +
                            "                                                                          \n" +
                            "                                                                          ");
        }

    }
}
