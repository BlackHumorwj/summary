package com.kotlin.for2021.apm

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/16
 *     desc   : Hook View 创建的过程
 *     version: 1.0
 * </pre>
 */

class LayoutInflaterFactory2(private val delegate: AppCompatDelegate) : LayoutInflater.Factory2 {


    val TAG = this.javaClass.name


    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

        val time = System.nanoTime()

        //反射创建View的过程
        val view = delegate.createView(parent, name, context, attrs)

        //耗时统计
        Log.i(TAG, name + " cost " + (System.nanoTime() - time))


        attrs.attributeCount

        for (i in 0 until attrs.attributeCount) {
            //遍历控件属性name 和 value
            Log.d(
                TAG,
                attrs.getAttributeName(i).toString() + " , " + attrs.getAttributeValue(i)
            )

        }

        return view

    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }


}