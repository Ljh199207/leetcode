package com.ljh.pattern.interceptingfilter;

/**
 * @author ljh
 * @date 2019-11-04 09:49
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
