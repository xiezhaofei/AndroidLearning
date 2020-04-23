package com.android.androidlearning.learningcode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiezhaofei on 2020-03-21
 * <p>
 * Describe:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HelloAnnotation {
    String say() default "hello world";
}
