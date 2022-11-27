package handler;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class ConsoleColorPrinter {

    private static final Map<Color, String> colors = new HashMap<>();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    static {
        colors.put(Color.BLACK, ANSI_BLACK);
        colors.put(Color.RED, ANSI_RED);
        colors.put(Color.GREEN, ANSI_GREEN);
        colors.put(Color.YELLOW, ANSI_YELLOW);
        colors.put(Color.BLUE, ANSI_BLUE);
        colors.put(Color.CYAN, ANSI_CYAN);
        colors.put(Color.WHITE, ANSI_WHITE);
    }

    private ConsoleColorPrinter() {}

    public static void print(String text, Color color) {
        String prefix = colors.getOrDefault(color, ANSI_WHITE);
        System.out.println(prefix + text + ANSI_RESET);
    }
}
