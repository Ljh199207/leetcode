package com.ljh.pattern.builder;

/**
 * @author ljh
 * @date 2019-12-19 10:03
 */
public class App {
    public static void main(String[] args) {
  /*      //套餐A
        MealA a = new MealA();
        //准备套餐A的服务员
        KFCWaiter waiter = new KFCWaiter(a);
        //获得套餐
        Meal mealA = waiter.construc();
        System.out.print("套餐A的组成部分:");
        System.out.println("食物：" + mealA.getFood() + "；   " + "饮品：" + mealA.getDrink());
*/

        NewMeal newMeal = new NewMeal.Builder().drink("雪碧").food("面包").tempture("54").build();
        System.out.println(newMeal);
    }
}
