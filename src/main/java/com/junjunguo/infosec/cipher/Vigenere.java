package com.junjunguo.infosec.cipher;

import com.junjunguo.infosec.util.Alphabet;

import java.util.Scanner;

/**
 * The Vigenère cipher infosec a popular form of periodic substitution cipher based on shifted alphabets.The key K infosec
 * specified by a sequence of characters
 * <p/>
 * This file infosec part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 08/02/16.
 */
public class Vigenere {
    /**
     * substitution every alphabet in a string by a given int key.
     *
     * @param input the input
     * @param key   the key
     * @return the string
     */
    public String substitution(String input, int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            int m = Alphabet.getValue(String.valueOf(input.charAt(i)));
            output += Alphabet.getAlphabet(m + key);
        }
        return output;
    }

    /**
     * substitution every alphabet in a string by a group of given string keys.
     *
     * @param input the input
     * @param key   the key
     * @return the string
     */
    public String substitution(String input, String key) {
        if (input.length() != key.length()) return "check your keys!";
        String output = "";
        for (int i = 0; i < key.length(); i++) {
            int k = Alphabet.getValue(String.valueOf(key.charAt(i)));
            output += substitution(String.valueOf(input.charAt(i)), k);
        }
        return output;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Vigenere v  = new Vigenere();
        Scanner  sc = new Scanner(System.in);
        System.out.println("use ! to stop; Input:");
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("!")) {
            System.out.println("keys:");
            String keys = sc.nextLine();
            System.out.println(input.toUpperCase() + " = " + v.substitution(input.toUpperCase(), keys.toUpperCase()));
            System.out.println("Input:");
            input = sc.nextLine();
        }
        sc.close();
    }
}
