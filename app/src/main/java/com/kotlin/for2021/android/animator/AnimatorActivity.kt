package com.kotlin.for2021.android.animator

import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import cn.enjoytoday.shadow.DimenUtil
import com.kotlin.for2021.R
import com.kotlin.for2021.android.BaseActivity
import com.nineoldandroids.animation.*


/**
 *
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/17
 *     desc   :
 *     version: 1.0
 *
 */

class AnimatorActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_animator
    }


    override fun initView() {
        findViewById<View>(R.id.llAddAccount).setOnClickListener {
            //translationX view的左上角相对父容器的偏移量
            val ofFloat = ObjectAnimator.ofFloat(it, "translationX", 0f, -DimenUtil.dp2px(70f))
            //设置属性值计算方式的接口
            ofFloat.setEvaluator(FloatEvaluator())
            //设置差值器
            ofFloat.interpolator = AccelerateDecelerateInterpolator()
            ofFloat.addUpdateListener {
                val animatedValue = it.animatedValue
            }
            ofFloat.start()
        }



        findViewById<TextView>(R.id.tvName).setOnClickListener {
            valueAnimatorFun(it as TextView)
        }
    }


    fun animatorSet() {

        val ofInt1 = ObjectAnimator.ofInt(1)
        val ofInt2 = ObjectAnimator.ofInt(1)
        val ofInt3 = ObjectAnimator.ofInt(1)
        val ofInt4 = ObjectAnimator.ofInt(1)
        val ofInt5 = ObjectAnimator.ofInt(1)

        AnimatorSet().apply {
            play(ofInt1).before(ofInt2)
            play(ofInt3).after(ofInt4)
            play(ofInt5).with(ofInt3)
            start()
        }

    }


    private fun valueAnimatorFun(view: TextView) {

        val valueAnimator = ValueAnimator().also {
            it.duration = 5000
            it.setObjectValues(FloatArray(2))
            it.interpolator = LinearInterpolator()

            //如何计算一个属性值
            it.setEvaluator(object : TypeEvaluator<FloatArray> {
                override fun evaluate(
                    fraction: Float,
                    startValue: FloatArray?,
                    endValue: FloatArray?
                ): FloatArray {
                    val temp = FloatArray(2)
                    temp[0] = fraction * 2
                    temp[1] = Math.random().toFloat() * 10 * fraction
                    return temp
                }
            })
        }

        valueAnimator.start()

        valueAnimator.addUpdateListener {
            val xyPos = it.animatedValue as FloatArray
            view.height = xyPos[0].toInt() //通过属性值设置View属性动画
            view.width = xyPos[1].toInt() //通过属性值设置View属性动

           // view.invalidate()
          //  view.requestLayout()

            Log.e("xxx","height = ${xyPos[0].toInt()}  width = ${xyPos[1].toInt()}")

//            view.setHeight(xyPos[0].toInt()); //通过属性值设置View属性动画
//            view.setWidth(xyPos[1].toInt()); //通过属性值设置View属性动画
        }


    }


}