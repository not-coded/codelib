package net.notcoded.codelib.util;

public class TickUtil {

    private static final int[] unitsInUnits = {20, 60, 60, 24};

    /**
     * Returns the amount of ticks in a timestamp.
     * @param ticks Ticks (20 ticks -> 1 second)
     * @return A String[] with the timestamp, e.g. 5:00, string[0], string[1]
     */
    public static String[] minuteTimeStamp(int ticks) {
        int[] numbers = {0, 0, ticks};
        int lastIndex = numbers.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            numbers[lastIndex - i - 1] = numbers[lastIndex - i] / unitsInUnits[i];
            numbers[lastIndex - i] %= unitsInUnits[i];
        }

        String[] text = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            text[i] = "" + numbers[i];
            if (text[i].length() == 1) text[i] = "0" + text[i];
        }

        return text;
    }


}
