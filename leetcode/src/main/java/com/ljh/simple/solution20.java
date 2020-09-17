package com.ljh.simple;

import java.util.Stack;

/**
 * 有效的括号{()[]}   只包含括号
 * 括号问题首先用的是栈
 */
public class solution20 {

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == '(' && s.charAt(i) != ')') {
                    return false;
                }
                if (stack.peek() == '{' && s.charAt(i) != '}') {
                    return false;
                }
                if (stack.peek() == '[' && s.charAt(i) != ']') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String a = "{()[}]";
        System.out.println(isValid(a));
    }
}


