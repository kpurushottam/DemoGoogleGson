package com.krp.android.demogooglegson.reflection.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by purushottam.kumar on 11/30/2015.
 */
public class SumFactory {
    public ISum createSum(Integer val1, Integer val2) {
        Sum sum = new Sum(val1, val2);
        SumHandler handler = new SumHandler(sum);
        Class[] interfacesArray = new Class[] {ISum.class};

        return (ISum) Proxy.newProxyInstance(Sum.class.getClassLoader(), interfacesArray, handler);
    }
}
