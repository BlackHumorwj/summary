package com.kotlin.for2021.android

import android.util.Log
import java.util.*

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

internal object LeakCanaryUtil {


    fun fn() {
        val key = UUID.randomUUID()

        Log.e("LeakCanaryUtil", " $key")

    }

}