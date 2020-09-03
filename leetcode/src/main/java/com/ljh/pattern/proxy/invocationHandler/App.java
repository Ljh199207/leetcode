package com.ljh.pattern.proxy.invocationHandler;

/**
 * @author ljh
 * @date 2020-03-23 15:32
 */
public class App {
    public static void main(String[] args) {
        Helloimplements hello = new Helloimplements();
        IHello ihello = (IHello) new DynaProxyHello().bind(hello);
        ihello.sayHello("123");
    }
}
