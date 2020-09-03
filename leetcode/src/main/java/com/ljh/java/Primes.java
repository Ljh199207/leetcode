package com.ljh.java;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ljh 查找素数有多少个
 * @date 2020-03-19 15:27
 */
public class Primes {

    public static void main(String[] args) {


        AtomicInteger conut = new AtomicInteger(0);
        int[] a = {2, 5, 3};

        for (int b : a) {
            conut.addAndGet(b);
        }

        System.out.println(conut.get());


    }
}
