package net.notcoded.codelib.util.numbers;

import java.util.TreeMap;

public class RomanNumbers {

    private final static TreeMap<Integer, String> romanNumbers = getRomanNumbers();
    private static TreeMap<Integer, String> getRomanNumbers() {
        TreeMap<Integer, String> romanNumbers = new TreeMap<>();
        romanNumbers.put(1000, "M");
        romanNumbers.put(900, "CM");
        romanNumbers.put(500, "D");
        romanNumbers.put(400, "CD");
        romanNumbers.put(100, "C");
        romanNumbers.put(90, "XC");
        romanNumbers.put(50, "L");
        romanNumbers.put(40, "XL");
        romanNumbers.put(10, "X");
        romanNumbers.put(9, "IX");
        romanNumbers.put(5, "V");
        romanNumbers.put(4, "IV");
        romanNumbers.put(1, "I");
        return romanNumbers;
    }

    /**
     * Turns the number into a roman numeral, e.g. 4 -> IV.
     * @param number The number that's going to get turned into a roman numeral.
     * @return A roman numeral as string.
     */
    public static String intToRoman(int number) {
        int floor =  romanNumbers.floorKey(number);
        if (number == floor) {
            return romanNumbers.get(number);
        }
        return romanNumbers.get(floor) + intToRoman(number - floor);
    }
}
