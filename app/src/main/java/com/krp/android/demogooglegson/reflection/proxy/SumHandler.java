package com.krp.android.demogooglegson.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by purushottam.kumar on 11/30/2015.
 */
public class SumHandler implements InvocationHandler {
    public Sum trueSum;

    public SumHandler(Sum sum) {
        this.trueSum = sum;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(trueSum, args);
        } catch (Exception e) {
            return new Integer(0);
        }
    }
}
