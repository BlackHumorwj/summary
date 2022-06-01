package com.kotlin.for2021.view.event

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.customview.widget.ViewDragHelper
import com.nineoldandroids.view.ViewHelper
import kotlin.math.roundToInt

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class MoveView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = this.javaClass::class.simpleName

    var lastX: Int = 0
    var lastY: Int = 0


    override fun onTouchEvent(event: MotionEvent): Boolean {

        val rawX = event.rawX
        val rawY = event.rawY

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_MOVE -> {
                val delX = rawX - lastX
                val delY = rawY - lastY
                Log.d(TAG, "move,delX =${delX} delY = $delY")
                val translationX = ViewHelper.getTranslationX(this) + delX
                val translationY = ViewHelper.getTranslationY(this) + delY

                //属性动画进行滑动
                ViewHelper.setTranslationX(this, translationX)
                ViewHelper.setTranslationY(this, translationY)

            }

            MotionEvent.ACTION_UP -> {

            }
        }

        lastX = rawX.roundToInt()
        lastY = rawY.roundToInt()


        return true
    }


}