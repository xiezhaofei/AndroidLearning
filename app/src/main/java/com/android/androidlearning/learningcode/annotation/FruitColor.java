package com.android.androidlearning.learningcode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiezhaofei on 2020-03-20
 * <p>
 * Describe:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface FruitColor {
    public enum Color {BULE, RED, GREEN}


    Color fruitColor() default Color.BULE;
}
