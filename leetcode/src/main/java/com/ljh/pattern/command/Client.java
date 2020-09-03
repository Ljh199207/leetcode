package com.ljh.pattern.command;

/**
 * @author ljh
 * @date 2020-05-18 14:27
 */
public class Client {

    public static void main(String[] args) {
        Invoker invokerA = new Invoker(new ConcreteCommandA(new Receiver()));
        invokerA.execute();
    }
}
