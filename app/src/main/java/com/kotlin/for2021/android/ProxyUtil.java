package com.kotlin.for2021.android;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/14
 *     desc   : 动态代理
 *     version: 1.0
 * </pre>
 */

class ProxyUtil {


    interface IApi{

    }


    private void init (){
        final Object proxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IApi.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



                return method.invoke(this,args);
            }
        });




    }



}
