package com.kotlin.for2021.android

import android.os.StrictMode

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/03
 *     desc   : StrictMode最常用于捕获应用程序主线程上的意外磁盘或网络访问。帮助我们让磁盘和网络操作远离主线程，可以
使应用程序更加平滑、响应更快。
https://cloud.tencent.com/developer/article/1735805
 *     version: 1.0
 * </pre>
 */

object StrictModeUtil {


    fun f1() {

        //线程检测策略
        //文件的读写
        //网络操作
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads() //磁盘读取操作
                .detectDiskWrites()//磁盘写入操作
                .detectNetwork() // 网络操作
                .penaltyLog()
                .build()
        )


//        StrictMode.setThreadPolicy(
//            StrictMode.ThreadPolicy.Builder().detectAll().build()


        StrictMode.setVmPolicy (StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()//Activity泄漏
                .detectLeakedSqlLiteObjects() //Sqlite对象泄露
                .detectLeakedClosableObjects() //未关闭的Closable对象泄露
                .penaltyLog() //违规打印日志
                .penaltyDeath() //违规崩溃
                .build())


    }


}