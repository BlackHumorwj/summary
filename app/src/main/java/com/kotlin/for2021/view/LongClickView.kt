package com.kotlin.for2021.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.math.abs

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/16
 *     desc   : 自定义长按事件
 *     version: 1.0
 * </pre>
 */

class LongClickView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TOUCH_SLOP = 20


    var runnable: Runnable = Runnable() {
        Toast.makeText(context, "长按了", Toast.LENGTH_LONG).show()
    }
    var dX = 0f
    var dY = 0f

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = event.x
                dY = event.y
                postDelayed(runnable, 5000)
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(event.x - dX) > TOUCH_SLOP || abs(event.y - dY) > TOUCH_SLOP) {
                    removeCallbacks(runnable)
                }
            }
            MotionEvent.ACTION_UP -> {
                removeCallbacks(runnable)
            }
        }

        return super.dispatchTouchEvent(event)
    }


}