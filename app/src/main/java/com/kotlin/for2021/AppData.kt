package com.kotlin.for2021

import android.app.Application
import android.content.Context
import android.util.Log
import com.kotlin.CrashHandler
import com.kotlin.for2021.android.ChoreographerHelper

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class AppData : Application() {

    companion object {
        public lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        Thread.currentThread().setUncaughtExceptionHandler(CrashHandler(this))

        ChoreographerHelper.start()

    }

}