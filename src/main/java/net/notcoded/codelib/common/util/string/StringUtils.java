package net.notcoded.codelib.common.util.string;

import java.util.Locale;

public class StringUtils {
    public static String firstArgumentToLowerCase(String input) {
        String[] args = input.split(" ", 2);
        args[0] = args[0].toLowerCase(Locale.ROOT);
        return String.join(" ", args);
    }
}
