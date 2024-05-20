package net.notcoded.codelib.util.string;

public class StringUtils {

    /**
     * Forces the first word of any {@code String} to be lowercase. (<STRONG>HI</STRONG>, THIS IS A COOL LIBRARY -> <STRONG>hi</STRONG>, THIS IS A COOL LIBRARY)
     * @param input The input.
     * @return The lowercased output.
     */
    public static String firstWordToLowerCase(String input) {
        String[] args = input.split(" ", 2);
        args[0] = args[0].toLowerCase();
        return String.join(" ", args);
    }
}
