package com.ljh.pattern.abstrctFactory;

/**
 * @author ljh
 * @date 2020-05-18 09:13
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("fill red");
    }
}
