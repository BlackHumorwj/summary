package com.kotlin.for2021.android.laucherMode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.os.Process
import android.widget.Button
import com.kotlin.for2021.R


/**
 *     author : jinBao
 *     time   : 2022/3/7  16:55
 *     desc   : https://www.jianshu.com/p/8aed27750f1e
 *
 */
class SecondModeActivity : AppCompatActivity() {


    /*
    5. 应该进入哪个任务栈？(难点)
        taskAffinity：官方翻译为亲和关系，而非栈名，表示更倾向于进入哪个栈。所以不是设置了该属性的Activity，就是在属于这个名的栈中。
        taskAffinity不设置时，则默认为包名；设置为空，则为当前Activity的包名路径
        当A启动一个声明为standard、singleTop的B时，且不带FLAG_ACTIVITY_NEW_TASK，则只会加入到A所在的栈顶，不会加入B所配置taskAffinity所声明的栈顶。
        不严谨的概括：只有singleTask、singleInstance或者带FLAG_ACTIVITY_NEW_TASK等带创建栈能力的方式启动，才会让taskAffinity生效。
        allowTaskReparenting这个属性，也会让taskAffinity生效。比如栈S1中的A启动设置了taskAffinity的B，无论B使用什么启动模式，B都会被放入其taskAffinity所声明的栈。

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_mode)
//跨进程传值
//2022-04-01 15:47:21.025 9080-9080/com.kotlin.for2021 E/xxxx: true --- com.kotlin.for2021
//2022-04-01 15:47:58.366 9329-9329/com.kotlin.for2021 E/xxxx: true --- com.kotlin.for2021:new
        Log.e(
            "xxxx",
            "${intent.getBooleanExtra("data", false).toString()} --- ${getProcessName(this)}"
        )

        var btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
          //此进程崩溃不会影响其他进程
            btn = null
            btn.text = ""

        }
    }




    fun getProcessName(cxt: Context): String? {
        val pid = Process.myPid()
        val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }


}
