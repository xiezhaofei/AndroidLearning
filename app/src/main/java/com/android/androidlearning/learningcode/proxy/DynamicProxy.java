package com.android.androidlearning.learningcode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiezhaofei on 2020-03-15
 * <p>
 * Describe:
 */
public class DynamicProxy implements InvocationHandler {
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke function");
        Object o = method.invoke(obj, args);
        System.out.println("before invoke function");
        return o;
    }
}
