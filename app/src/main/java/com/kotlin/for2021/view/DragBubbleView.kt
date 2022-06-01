package com.kotlin.for2021.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import cn.enjoytoday.shadow.DimenUtil
import com.kotlin.for2021.R
import kotlin.math.sqrt

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class DragBubbleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    //外面圆心位置
    private var mDragPoint: PointF = PointF()

    //内部固定圆心位置
    private var mFixedPoint: PointF = PointF()

    private val FIX_RADIUS_MAX = dp2px(10f)
    private val FIX_RADIUS_MIN = dp2px(2f)

    private val DRAG_RADIUS_MAX = dp2px(15f)


    private var mPaint: Paint = Paint()

    init {
        mPaint.color = ContextCompat.getColor(context, R.color.purple_700)
        mPaint.isAntiAlias = true
        mPaint.isDither = true

    }


    override fun onDraw(canvas: Canvas) {
        // 1.绘制拖拽圆
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, DRAG_RADIUS_MAX, mPaint)


        // 计算两个圆之间的距离
        val distance =
            sqrt(((mDragPoint.x - mFixedPoint.x) * (mDragPoint.x - mFixedPoint.x) + (mDragPoint.y - mFixedPoint.y) * (mDragPoint.y - mFixedPoint.y)).toDouble())

        // 计算固定圆的半径，距离越大圆半径越小
        val fixRadius = (FIX_RADIUS_MAX - distance / 14).toFloat()

        if (FIX_RADIUS_MIN < fixRadius) {
            // 2.绘制固定圆
            canvas.drawCircle(
                mFixedPoint.x, mFixedPoint.y,
                fixRadius, mPaint
            )
        }


    }

    private fun dp2px(i: Float): Float {
        return DimenUtil.dp2px(i)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //记录当前圆点
                val downX = event.x
                val downY = event.y
                initPoint(downX, downY)
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x
                val moveY = event.y
                updateDragPoint(moveX, moveY)
            }
        }
        invalidate()
        return true
    }

    /**
     * 更新拖拽圆的位置
     * @param x Float
     * @param y Float
     */
    private fun updateDragPoint(x: Float, y: Float) {
        mDragPoint.x = x
        mDragPoint.y = y
    }

    /**
     * 初始化圆的位置
     * @param x Float
     * @param y Float
     */
    private fun initPoint(x: Float, y: Float) {
        mDragPoint.x = x
        mDragPoint.y = y

        mFixedPoint.x = x
        mFixedPoint.y = y
    }

}