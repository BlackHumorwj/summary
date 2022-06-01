package com.kotlin.for2021.view.draw

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/16
 *     desc   : 绘制圆角View
 *     version: 1.0
 * </pre>
 */

class DrawRoundView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    init {
        //  setBackgroundDrawable(  ColorDrawable(0x33ff0000));

    }

    //    override fun dispatchDraw(canvas: Canvas) {
//        val save = canvas.save()
//        val path = Path()
//        path.addRoundRect(RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat()), floatArrayOf(50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f), Path.Direction.CW)
//        canvas.clipPath(path)
//        canvas.restoreToCount(save)
//        super.dispatchDraw(canvas)
//    }
//
//
    override fun draw(canvas: Canvas) {

        val save = canvas.save()

        val path = Path()

        path.fillType = Path.FillType.EVEN_ODD

        //Path.Direction.CW 顺时针
        path.addRoundRect(RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat()), floatArrayOf(50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f), Path.Direction.CW)

        canvas.clipPath(path)

        super.draw(canvas)

        canvas.restoreToCount(save)


    }

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun dispatchDraw(canvas: Canvas) {
//          canvas.saveLayer(RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat()), Paint())
//
//        super.dispatchDraw(canvas)
//
//        val path = Path()
//
//        path.fillType = Path.FillType.EVEN_ODD
//
//
//        path.addRoundRect(RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat()), floatArrayOf(50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f), Path.Direction.CW)
//        canvas.clipPath(path)
//
//        canvas.restore()
//
//    }


}