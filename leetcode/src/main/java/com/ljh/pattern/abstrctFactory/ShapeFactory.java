package com.ljh.pattern.abstrctFactory;

/**
 * @author ljh
 * @date 2020-05-18 09:10
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if ("Circle".equalsIgnoreCase(shape)) {
            return new Circle();
        } else if ("Sequer".equalsIgnoreCase(shape)) {
            return new Sequer();

        } else if ("Rectangle".equalsIgnoreCase(shape)) {
            return new Rectangle();
        }
        return null;
    }
}
