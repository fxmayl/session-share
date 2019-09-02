/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年08月05日 20:07
 */
public class Java8Test {
    public static boolean isContain(Filter filter) {
        return filter.isContain();
    }

    public static Apple getApple(Apple apple, Apple.AppleInit appleInit) {
        if (apple == null) {
            return appleInit.get();
        }
        return apple;
    }

    public static Fruit getFruit(Apple apple, Apple.AppleFunction appleFunction) {
        if (apple != null) {
            return appleFunction.apply(apple);
        } else {
            return appleFunction.apply(getApple(apple, () -> new Apple()));
        }
    }

    public static void main(String[] args) {
        boolean f = isContain(() -> "abc".contains("a"));
        System.out.println(f);

        Predicate<Apple> predicate = apple -> "green".equals(apple.getColor());

        Function<Apple, Boolean> function = apple -> "green".equals(apple.getColor());

        Consumer<Apple> consumer = apple -> {
            if (apple.getColor().equals("green")) {
                apple.setColor("yellow");
            }
        };

        Supplier<Apple> supplier = () -> new Apple("yellow", 180L);

        Apple apple = getApple(null, () -> new Apple("red", 100L));

        System.out.println(apple);

        Fruit fruit = getFruit(apple, a -> {
            Fruit fruit1 = new Fruit();
            fruit1.setApple(a);
            return fruit1;
        });

        System.out.println(fruit);

        System.out.println(getFruit(null, a -> {
            Fruit fruit1 = new Fruit();
            fruit1.setApple(a);
            return fruit1;
        }));

        ttt();
    }

    private static void ttt() {
        label1:
        for (int i = 0; i < 10; i++) {
            label2:
            if (i > 0) {
                if (i > 8) {
                    break label1;
                }
                System.out.print(i + " ");
                if (i > 3) {
                    break label2;
                }
                System.out.print(" if end ");

            }

            label3: {
                if (i > 5) {
                    break label3;
                }
                System.out.print(", label3 block");
            }
            System.out.println(", for end ");
        }
    }

    @FunctionalInterface
    public interface Filter {
        boolean isContain();
    }
}
