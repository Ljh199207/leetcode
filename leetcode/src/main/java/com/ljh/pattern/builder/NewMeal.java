package com.ljh.pattern.builder;

/**
 * @author ljh
 * @date 2019-12-19 10:07
 */
public class NewMeal {

    private String food;
    private String drink;
    private String tempture;

    public NewMeal(Builder builder) {
        this.food = builder.food;
        this.drink = builder.drink;
        this.tempture = builder.tempture;
    }

    public static final class Builder{
        private String food;
        private String drink;
        private String tempture;

        public Builder(){

        }
       public Builder food(String val){
            food=val;
            return this;
       }
       public Builder drink(String val){
            drink=val;
            return this;
       }
       public Builder tempture(String val){
            tempture= val;
            return this;
       }

       public NewMeal build(){
            return  new NewMeal(this);
       }
    }
}
