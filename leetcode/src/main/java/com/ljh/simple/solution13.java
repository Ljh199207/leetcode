package com.ljh.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转成整数
 * I V  X  L   C   D   M
 * 1 5 10  50  100 500 1000
 */
public class solution13 {


    public static int luoMaToInt(String s) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "MD";
        System.out.println(luoMaToInt(s));
    }
}
