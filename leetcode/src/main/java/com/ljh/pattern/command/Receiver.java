package com.ljh.pattern.command;

/**
 * @author ljh
 * @date 2020-05-18 14:20
 */
public class Receiver {

    public void methodA() {
        System.out.println("执行A");
    }

    public void methodB() {
        System.out.println("执行B");
    }

    public void undo() {
        System.out.println("撤销");
    }

}
