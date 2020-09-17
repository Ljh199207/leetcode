package com.ljh.pattern.builder;

/**
 * @author ljh
 * @date 2019-12-19 09:56
 */
public class MealB extends MealBuilder {
    @Override
    public void buildFood() {
        meal.setFood("薯条");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("可乐");
    }
}
