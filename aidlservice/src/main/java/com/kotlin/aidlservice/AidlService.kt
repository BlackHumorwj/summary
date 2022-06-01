package com.kotlin.aidlservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.math.log

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/06/01
 *     desc   : 通过Service对外提供服务
 *     version: 1.0
 * </pre>
 */

class AidlService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    private var mBinder = object : IBlogManager.Stub() {
        override fun pullFromService(): String {
            return "hello"

        }

        override fun pushToService(info: BlogInfo?) {
             Log.e("xxx", info?.name.toString())
        }

        override fun getVersionCode(): Int {
            return 12
        }

    }

}