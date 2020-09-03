package com.ljh.pattern.proxy.invocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ljh
 * @date 2020-03-23 15:12
 */
public class DynaProxyHello implements InvocationHandler {

    private Object object;

    public Object bind(Object object){
        this.object = object;
        return Proxy.newProxyInstance(this.object.getClass().getClassLoader(),this.object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        System.out.println("调用代理方法"+method.getName());
        result = method.invoke(object,objects);
        return result;
    }
}
