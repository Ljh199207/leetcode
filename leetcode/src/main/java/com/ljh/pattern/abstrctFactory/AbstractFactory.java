package com.ljh.pattern.abstrctFactory;

/**
 * @author ljh
 * @date 2020-05-18 09:09
 */
public abstract class AbstractFactory {

   public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
