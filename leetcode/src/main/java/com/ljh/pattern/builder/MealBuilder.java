package com.ljh.pattern.builder;

/**
 * @author ljh
 * @date 2019-12-19 09:54
 */
public abstract class MealBuilder {
    Meal meal = new Meal();

    public abstract void buildFood();

    public abstract void buildDrink();

    public Meal getMeal(){
        return meal;
    }
}
