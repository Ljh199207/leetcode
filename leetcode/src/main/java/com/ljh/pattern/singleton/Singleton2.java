package com.ljh.pattern.singleton;

/**
 * @author ljh 饿汉式
 * @date 2020-05-18 09:38
 */
public class Singleton2 {

    // 直接初始化静态变量。这样就保证了线程安全
    private static Singleton2 singleton = new Singleton2();

    private Singleton2() {

    }

    public Singleton2 getSingletion2() {
        return singleton;
    }


}
