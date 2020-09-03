package com.ljh.pattern.proxy.invocationHandler;

/**
 * @author ljh
 * @date 2020-03-23 15:07
 */
public class Helloimplements implements IHello {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    @Override
    public void sayGoogBye(String name) {
        System.out.println(name+" GoodBye!");

    }
}
