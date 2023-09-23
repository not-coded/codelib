package net.notcoded.codelib.util.string;

public class StringUtils {
    public static String firstArgumentToLowerCase(String input) {
        String[] args = input.split(" ", 2);
        args[0] = args[0].toLowerCase();
        return String.join(" ", args);
    }
}
