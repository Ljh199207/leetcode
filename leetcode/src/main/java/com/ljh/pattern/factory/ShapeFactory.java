package com.ljh.pattern.factory;

/**
 * @author ljh
 * @date 2019-11-04 09:36
 */
public class ShapeFactory {
    /**
     * 使用 getShape 方法获取形状类型的对象
     */
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if("CIRCLE".equalsIgnoreCase(shapeType)){
            return new Circle();
        } else if("RECTANGLE".equalsIgnoreCase(shapeType)){
            return new Rectangle();
        } else if("SQUARE".equalsIgnoreCase(shapeType)){
            return new Square();
        }
        return null;
    }

}
