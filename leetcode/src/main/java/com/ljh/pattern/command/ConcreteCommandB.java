package com.ljh.pattern.command;

/**
 * @author ljh
 * @date 2020-05-18 14:23
 */
public class ConcreteCommandB implements Command {

    private Receiver receiver;

    public ConcreteCommandB(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.methodB();
    }
}
