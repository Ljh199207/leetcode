package com.ljh.pattern.factory;

/**
 * @author ljh
 * @date 2019-11-04 09:34
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
