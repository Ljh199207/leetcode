package com.ljh.pattern.command;


/**
 * @author ljh
 * @date 2020-05-18 14:19
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
