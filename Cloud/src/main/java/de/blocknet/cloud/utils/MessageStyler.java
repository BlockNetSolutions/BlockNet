package de.blocknet.cloud.utils;

import de.blocknet.cloud.Main;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public class MessageStyler {


    public static void sendInfo(String message) {
        Main.getLogger().info(getFormattedString(message));
    }

    public static void sendWarning(String message) {
        Main.getLogger().warning(getFormattedString(message));
    }

    public static String getFormattedString(String message) {
        AttributedStyle style = AttributedStyle.DEFAULT;
        String[] parts = message.split("§");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (part.length() == 0) continue;
            int color = getColor(part.charAt(0));
            if (color >= 0) {
                sb.append(getAttributedStyle(part.substring(1), style.foreground(color)));
            } else {
                sb.append(getAttributedStyle("§" + part, style));
            }
        }
        return sb.toString();
    }

    private static int getColor(char c) {
        switch (c) {
            case '0': return AttributedStyle.BLACK;
            case '1': return AttributedStyle.RED;
            case '2': return AttributedStyle.GREEN;
            case '3': return AttributedStyle.YELLOW;
            case '4': return AttributedStyle.BLUE;
            case '5': return AttributedStyle.MAGENTA;
            case '6': return AttributedStyle.CYAN;
            case '7': return AttributedStyle.WHITE;
            case '8': return AttributedStyle.BRIGHT;
            default: return -1;
        }
    }

    private static String getAttributedStyle(String message, AttributedStyle style) {
        return new AttributedString(message, style).toAnsi();
    }
}

