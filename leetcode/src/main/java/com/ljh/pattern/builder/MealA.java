package com.ljh.pattern.builder;

/**
 * @author ljh
 * @date 2019-12-19 09:56
 */
public class MealA extends MealBuilder {
    @Override
    public void buildFood() {
        meal.setFood("鸡翅");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("柠檬果汁");
    }
}
