package com.ljh.pattern.interceptingfilter;

/**
 * @author ljh
 * @date 2019-11-04 09:49
 */
public class DebugFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("request log: " + request);
    }
}
