package com.kotlin.for2021.base

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/03
 *     desc   : Hook的使用
 *     version: 1.0
 * </pre>
 */

// internal 限制了跨 module 的方法的使用
internal object HookUtil {

    /*基础
    * 1、反射api
    * 2、动态代理
    * 3、静态代理
    * */


    /*
    * 1、根据需求确定要Hook的对象
    * 2、寻找要Hook的对象的持有者，拿到要Hook的对象
    * 3、定义要 Hook的对象的代理类，并且创建该类的对象
    * 4、使用上一步创建出来的对象，替换掉要Hook的对象
    * */


    //FIXME

    /**
     * 替换View的点击事件
     */
    @SuppressLint("DiscouragedPrivateApi")
    fun hook(context: Context, view: View) {


        //---------------反射获取对象中的私有方法并执行

        //通过View的 getListenerInfo() 方法拿到 ListenerInfo对象
        //反射创建对象 并 获取对象方法
        val methodGetListenerInfo = View::class.java.getDeclaredMethod("getListenerInfo")

        methodGetListenerInfo.isAccessible = true

        //调用方法获取 mListenerInfo ，再通过 mListenerInfo 拿到成员变量 mOnClickListener
        //反射调用对象方法
        val mListenerInfo = methodGetListenerInfo.invoke(view)


        //------------------反射获取对象中的 成员变量

        val listenerInfoClazz = Class.forName("android.view.View\$ListenerInfo")
        val field = listenerInfoClazz.getDeclaredField("mOnClickListener")
        //View原始的监听   通过对象mListenerInfo 获取字段  mOnClickListener
        val onClickListener = field.get(mListenerInfo)


        //---------------------通过动态代理,拿到代理对象并替換原始对象
        //返回代理对象
        val proxyOnClickListener = Proxy.newProxyInstance(
            context.javaClass.classLoader,
            arrayOf(View.OnClickListener::class.java),
            object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>): Any? {
                    //处理自己的逻辑
                    Log.e("HookUtil", "do sth")


                    //Kotlin中数组转为可变长参数，是通过前面加*
                    return method?.invoke(onClickListener, *args)
                }

            })

        // set()方法含义 将对象mListenerInfo的值mOnClickListener设置为新value proxyOnClickListener
        field.set(mListenerInfo, proxyOnClickListener)

    }

    /**
     * <pre>
     *     author : jinBao
     *     time   : 2022/3/3  15:34
     *     desc   : 静态代理类
     * </pre>
     */
    class ProxyClickListener(val listener: View.OnClickListener) : View.OnClickListener {

        override fun onClick(v: View?) {

            //
            Log.e("HookUtil", "do sth")

            listener.onClick(v)
        }

    }

    fun reflect(view: View) {

        //方式一
        val clazz = View::class.java


        val method = clazz.getDeclaredMethod("getListenerInfo")
        method.isAccessible = true
        val listenerInfo = method.invoke(view)


        //方式二
        val forNameClazz = Class.forName("android.view.View\$ListenerInfo")

        val field = forNameClazz.getDeclaredField("mOnClickListener")

        val orgListener = field.get(listenerInfo)

        val newListener = ProxyClickListener(orgListener as View.OnClickListener)

        //修改对象listenerInfo中mOnClickListener字段为  newListener
        field.set(listenerInfo, newListener)

    }


}