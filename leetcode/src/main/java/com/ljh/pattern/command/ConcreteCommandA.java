package com.ljh.pattern.command;

/**
 * @author ljh
 * @date 2020-05-18 14:18
 */
public class ConcreteCommandA implements Command {

    private Receiver reciver;

    public ConcreteCommandA(Receiver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void execute() {
        reciver.methodA();
    }
}
