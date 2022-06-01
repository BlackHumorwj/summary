package com.kotlin.for2021.android

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Printer
import android.view.Choreographer
import com.kotlin.for2021.util.LogUtil

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object ChoreographerHelper {


    fun start() {

        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {

            //上次回调时间
            var lastFrameTimeNanos: Long = 0

            override fun doFrame(frameTimeNanos: Long) {

                if (lastFrameTimeNanos == 0L) {
                    lastFrameTimeNanos = frameTimeNanos
                    Choreographer.getInstance().postFrameCallback(this)
                    return
                }

                var diff = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000 //ns-> ms

                if (diff > 16.6f) {
                    //掉帧数据
                    var droppedCount = diff / 16.6
                    LogUtil.i(this@ChoreographerHelper, droppedCount)
                }

                lastFrameTimeNanos = frameTimeNanos
                Choreographer.getInstance().postFrameCallback(this)
            }
        })

    }


}


class BlockCanary {

    init {
        Looper.getMainLooper().setMessageLogging(LogMonitor())
    }

}


class LogMonitor : Printer {



    init {
        val handlerThread = HandlerThread("")
        handlerThread.start()

        val handler = Handler(handlerThread.looper)


    }


    override fun println(x: String?) {

    }

}
