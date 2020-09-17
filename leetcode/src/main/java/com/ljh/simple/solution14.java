package com.ljh.simple;

/**
 * 最长公共前缀
 */
public class solution14 {


    public static String commonFix(String[] strings) {
        if (strings.length == 0) {
            return "";
        }
        String result;
        String prefix = "";
        boolean broken = false;
        int i = 0;
        while (true) {
            i++;
            result = prefix;
            if (i > strings[0].length()) {
                break;
            }
            prefix = strings[0].substring(0,i);
            for (String s: strings){
             if(i>s.length()||!s.startsWith(prefix)){
                 broken = true;
                 break;
             }
            }
            if(broken){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s[] ={"flower","flower","flower"};
        System.out.println(commonFix(s));
    }

}
