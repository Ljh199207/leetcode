package com.ljh.pattern.singleton;

/**
 * @author ljh 双重锁
 * @date 2020-05-18 09:38
 */
public class Singletion3 {

    // 直接初始化静态变量。这样就保证了线程安全
    private volatile static Singletion3 singleton;

    private Singletion3() {

    }

    // 双重锁 校验
    public static Singletion3 getSingletion2() {
        if (singleton == null) {
            synchronized (Singletion3.class) {
                if (singleton == null) {
                    singleton = new Singletion3();
                }
            }
        }
        return singleton;
    }

}
