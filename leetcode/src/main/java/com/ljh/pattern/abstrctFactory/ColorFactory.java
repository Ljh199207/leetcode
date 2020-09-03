package com.ljh.pattern.abstrctFactory;

/**
 * @author ljh
 * @date 2020-05-18 09:10
 */
public class ColorFactory extends AbstractFactory {


    @Override
    public Color getColor(String color) {
        if ("Red".equalsIgnoreCase(color)) {
            return new Red();
        } else if ("Blue".equalsIgnoreCase(color)) {
            return new Blue();
        } else if ("Green".equalsIgnoreCase(color)) {
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
