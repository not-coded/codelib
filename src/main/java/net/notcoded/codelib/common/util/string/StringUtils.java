package net.notcoded.codelib.common.util.string;

import java.util.Locale;

public class StringUtils {
    /**
     * Forces the first word of any {@code String} to be lowercase. (<STRONG>HI</STRONG>, THIS IS A COOL LIBRARY -> <STRONG>hi</STRONG>, THIS IS A COOL LIBRARY)
     * @param input The input.
     * @return The lowercased output.
     */
    public static String firstArgumentToLowerCase(String input) {
        String[] args = input.split(" ", 2);
        args[0] = args[0].toLowerCase(Locale.ROOT);
        return String.join(" ", args);
    }
}
