package com.ljh.pattern.factory;

/**
 * @author ljh
 * @date 2019-11-04 09:36
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
