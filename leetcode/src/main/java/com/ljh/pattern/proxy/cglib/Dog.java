package com.ljh.pattern.proxy.cglib;

/**
 * @author ljh
 * @date 2020-03-30 10:02
 */
public class Dog {
    final public void run(String name) {
        System.out.println("狗" + name + "----run");
    }

    public void eat() {
        System.out.println("狗----eat");
    }
}
