package com.cx.test;

import java.util.Arrays;
import java.util.Comparator;

public class StrLenCompartor implements Comparator<String> {


    @Override
    public int compare(String o1, String o2) {
        return 0;
    }

    public static void main(String[] args) {
        String s = "abcde";


        String[] split = s.split(",");
        System.out.println(Arrays.asList(split));


    }
}