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
import android.graphics.RectF


/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/16
 *     desc   : 绘制圆角View
 *     version: 1.0
 * </pre>
 */

//https://www.jianshu.com/p/7820db3ed504

class DrawRoundView02(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    var roundPaint = Paint()
    var imagePaint = Paint()
    private val cornerRadius = 50f

    init {
        //画笔填充模式
        roundPaint.style = Paint.Style.FILL
        //https://www.jianshu.com/p/d11892bbe055
        roundPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT) //图形混合模式,覆盖原图上
        roundPaint.isAntiAlias = true

        // imagePaint.xfermode = null
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun dispatchDraw(canvas: Canvas) {
        //保存还没有绘制之前的图层
        canvas.saveLayer(RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat()), imagePaint)
        super.dispatchDraw(canvas)

        drawTopLeft(canvas)

        //恢复之前的图层，要不然背景是黑色的
        canvas.restore()

    }

    private fun drawTopLeft(canvas: Canvas) {
        val path = Path()


        path.moveTo(0f, cornerRadius)
        path.lineTo(0f, 0f)//划线
        path.lineTo(cornerRadius, 0f)//划线

        path.arcTo(RectF(0f, 0f, cornerRadius * 2, cornerRadius * 2), -90f, -90f)//画一个圆弧

        //构成一个封闭的图形
        path.close()

        //
        canvas.drawPath(path, roundPaint)
    }


}