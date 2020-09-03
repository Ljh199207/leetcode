package com.ljh.pattern.singleton;

/**
 * @author ljh
 * @date 2020-05-18 09:38
 */
public class Singleton {


    private static Singleton singleton;

    private Singleton() {

    }

    //多线程是不安全的
    public Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    // synchronized 同步处理
    public synchronized Singleton singleton2() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    // 直接初始化静态变量。这样就保证了线程安全
   // private static Singleton singleton = new Singleton();

}
