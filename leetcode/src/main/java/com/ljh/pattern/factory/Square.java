package com.ljh.pattern.factory;

/**
 * @author ljh
 * @date 2019-11-04 09:35
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
