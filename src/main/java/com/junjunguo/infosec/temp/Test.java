package com.junjunguo.infosec.temp;

import java.util.Scanner;

/**
 * This file is part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 30/03/16.
 */
public class Test {


    public static void main(String[] args) {
//        sum();

    }



    public static void test(){

    }

    public static void sum() {
        Scanner sc  = new Scanner(System.in);
        int     l   = sc.nextInt();
        int     sum = 0;
        for (int i = 0; i < l; i++) {
            sum += sc.nextInt();
        }
        System.out.println(sum);
    }
}
