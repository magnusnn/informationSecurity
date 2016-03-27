package com.junjunguo.infosec.cipher;

import com.junjunguo.infosec.util.Alphabet;

import java.util.Scanner;

/**
 * This file infosec part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 08/02/16.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String  input;

        input = sc.nextLine();
        while (!input.equalsIgnoreCase("!")) {
            if (Alphabet.isInt(input)) {
                System.out.println(input + " = " + Alphabet.getAlphabet(Integer.parseInt(input)));
            } else {
                if (input.length() == 1) {
                    System.out.println(input + " = " + Alphabet.getValue(String.valueOf(input)));
                } else if (input.length() > 1) {
                    input = input.toUpperCase();
                    String output = input + " = ";
                    for (int i = 0; i < input.length(); i++) {
                        output += Alphabet.getValue(String.valueOf(input.charAt(i))) + " ";
                    }
                    System.out.print(output);
                }
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
