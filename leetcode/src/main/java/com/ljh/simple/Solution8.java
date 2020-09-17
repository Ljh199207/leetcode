package com.ljh.simple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author ljh
 * @date 2019-11-18 17:25
 * 其能将字符串转换成整数
 */
public class Solution8 {

    public static void main(String[] args) {
            String  s  ="   -45654565465465";
        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String str){
        //清空字符串开头和末尾空格（这是trim方法功能，事实上我们只需清空开头空格）
        str = str.trim();
        //java正则表达式
        Pattern p = compile("^[\\+\\-]?\\d+");
        Matcher m = p.matcher(str);
        int value = 0;
        if(m.find()){
            value = Integer.parseInt(str.substring(m.start(), m.end()));
        }
        return value;
    }
}
