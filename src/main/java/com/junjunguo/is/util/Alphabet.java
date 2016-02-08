package com.junjunguo.is.util;

/**
 * This file is part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 08/02/16.
 */
public class Alphabet {


    /**
     * Gets alphabet: 0 = A 25 = Z 26 = -
     *
     * @param value the value
     * @return the alphabet
     */
    public static String getAlphabet(int value) {
        value = value % 27;
        if (value == 26) {
            return "-";
        }
        return String.valueOf(Character.toChars(value + 65));
    }


    /**
     * Gets value.
     *
     * @param alphabet the alphabet
     * @return the value
     */
    public static int getValue(String alphabet) {
        if (alphabet.equalsIgnoreCase("-")) {
            return 26;
        }
        return Character.valueOf(alphabet.charAt(0)) - 65;
    }

    /**
     * Is the given string int boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
