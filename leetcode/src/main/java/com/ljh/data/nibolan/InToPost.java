package com.ljh.data.nibolan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ljh
 * @date 2020-09-03 14:07
 * 逆波兰表达式  中缀转后缀
 */
public class InToPost {


    public static List convent(String expression) {
        List<String> list = new ArrayList<>();

        int i = 0;
        String str; //对多位数进行拼接
        char c; //遍历到一个字符，就放入c
        do {
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 59) {
                list.add("" + c);
                i++;
            } else {
                str = "";
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }
        while (i < expression.length());
        return list;
    }

    public static List<String> parseSuffixExpression(List<String> list) {
        //储存中间结果栈  没有出栈
        List<String> s2 = new ArrayList<>();
        //存储符号栈
        Stack<String> s1 = new Stack<>();

        for (String item : list) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (!s1.isEmpty() && !s1.peek().equals("(") && operationParen(s1.peek()) >= operationParen(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    public static List<String> CoverInToPost(List<String> list) {
        //储存中间结果栈
        List<String> numStack = new ArrayList<>();
        //存储符号栈
        Stack<String> calStack = new Stack<>();
        //1 从左到右扫描中缀表达式，若是操作数，直接存入 post 栈
        for (String e : list) {
            if (e.matches("\\d+")) {
                numStack.add(e);
            } else {
                //2 如果是"("直接入栈
                if (e.equals("(")) {
                    calStack.push(e);
                } else if (e.equals(")")) {
                    while (true) {
                        String cal = calStack.pop();
                        if (cal.equals("(")) {
                            break;
                        } else {
                            numStack.add(cal);
                        }
                    }
                }
                //（2）如果是运算符（'+'、'-'、'*'、'/'），先判断数组opera的栈顶的操作数的优先级（如果是空栈那么直接入栈到数组opera），
                // 如果是左括号那么直接入栈到数组opera中，如果栈顶是运算符，且栈顶运算符的优先级大于该运算符
                else {
                    while ((!calStack.empty()) && (!calStack.peek().equals("(")) && (operationParen(e) <= operationParen(calStack.peek()))) {
                        numStack.add(calStack.pop());
                    }
                    calStack.push(e);
                }
            }
        }
        while (true) {
            if (calStack.empty()) {
                break;
            }
            numStack.add(calStack.pop());
        }

        return numStack;
    }

    public static int operationParen(String s) {
        int result = 0;
        final int DIV = 2;
        final int MUL = 2;
        final int ADD = 1;
        final int SUB = 1;
        switch (s) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("操作符不合要求");
                break;
        }
        return result;
    }


    public static void main(String[] args) {
        String expression = "2+(20+3)*5-21";

        List convent = convent(expression);
        System.out.println(convent);
        List list2 = CoverInToPost(convent);
        System.out.println(list2);
        List list = parseSuffixExpression(convent);
        System.out.println(list);
    }


}
