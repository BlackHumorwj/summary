package com.kotlin.for2021.view.event

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ListView
import kotlin.math.abs

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class MyListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs) {


    var lastX = 0
    var lastY = 0

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        var x = ev.x
        var y = ev.y


        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                //down 事件父容器不进行拦截
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val detX = x - lastX
                val delY = y - lastY
                if (abs(delY) < abs(detX)) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    Log.e("dispatchTouchEvent", "dispatchTouchEvent")
                }
            }
            MotionEvent.ACTION_UP -> {

            }
        }

        lastX = x.toInt()
        lastY = y.toInt()

        return super.dispatchTouchEvent(ev)
    }


}