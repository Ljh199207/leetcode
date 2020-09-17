package com.ljh.pattern.interceptingfilter;

/**
 * @author ljh
 * @date 2019-11-04 09:49
 */
public class Target {
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }
}
