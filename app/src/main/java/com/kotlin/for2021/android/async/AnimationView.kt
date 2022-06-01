package com.kotlin.for2021.android.async

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import com.google.android.material.animation.AnimationUtils
import com.nineoldandroids.animation.ObjectAnimator

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class AnimationView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {


    init {

        val objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 45f);
        objectAnimator.duration = 300
        objectAnimator.interpolator = BounceInterpolator()//策略模式
//        Handler(Looper.getMainLooper()).postDelayed({
//            objectAnimator.start()
//        }, 0)


        post {
            objectAnimator.start()
        }

      //  objectAnimator.start()
    }


}