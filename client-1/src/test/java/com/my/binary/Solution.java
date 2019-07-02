package com.my.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-06-30 15:01
 **/
public class Solution {
    public  static int numberOfOne(int n) {
        StringBuffer bin = new StringBuffer();
        if (n == Integer.MIN_VALUE) {
            return 1;
        }

        int b = n;

        if (n < 0) {
            b = (-1) * n;
        }


        int k = b / 2;
        int y = b % 2;
        while (k != 0) {
            bin.append(y);
            y = k % 2;
            k = k / 2;
        }
        bin.append(y);

        char[] array = bin.toString().toCharArray();
        int sum = 0;
        if (n >= 0) {
            for (int i = 0; i < array.length; i++) {
                if ('1' == array[i]) {
                    sum += 1;
                }
            }

        } else {
            char[] reserveArray = new char[32];
            for (int i = 0; i < reserveArray.length; i++) {
                if (i > array.length - 1) {
                    reserveArray[i] = '1';
                    continue;
                }

                if ('1' == array[i]) {
                    reserveArray[i] = '0';
                } else {
                    reserveArray[i] = '1';
                }
            }


            for (int i = 0; i < reserveArray.length; i++) {
                if (reserveArray[i] == '0') {
                    reserveArray[i] = '1';
                    break;
                } else {
                    reserveArray[i] = '0';
                }
            }

            for (int i = reserveArray.length - 1; i >= 0; i--) {
                if (reserveArray[i] == '1') {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    public static double power(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }

        if (exponent == 2) {
            return base * base;
        }
        double sum = 1;
        for (int i = 0; i < exponent; i++) {
            sum = sum * base;
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(numberOfOne(-1));
//
//        System.out.println(power(2,3));

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1,"h");
        map.put(2,"e");
        map.put(3,"l");
        map.put(4,"l");
        map.put(5,"o");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println(next.getKey() + "::::" + next.getValue());
        }

        new ArrayList<>(map.values());
    }
}
