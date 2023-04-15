package de.blocknet.cloud.utils;

import de.blocknet.cloud.Main;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public class MessageStyler {


    public static void sendInfo(String message, AttributedStyle style, int color) {
        Main.getLogger().info(new AttributedString(message, style.foreground(color)).toAnsi());
    }
    public static void sendWarning(String message, AttributedStyle style, int color) {
        Main.getLogger().warning(new AttributedString(message, style.foreground(color)).toAnsi());
    }

}
