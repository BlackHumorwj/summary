package com.kotlin.for2021.apm

import android.os.Build
import android.os.FileObserver
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class ANRTest {


    @RequiresApi(Build.VERSION_CODES.Q)
    fun test(){

        //该方案因为高版本文件权限的问题，目前只支持<=21的版本。

        //监听ANR目录文件变化
        val fileObserver = MyFileObserver(File("/data/anr/"))

        //开始监听
        fileObserver.startWatching()

        //停止监听
        fileObserver.stopWatching()


    }

}

@RequiresApi(Build.VERSION_CODES.Q)
class MyFileObserver(path: File) : FileObserver(path) {

    override fun onEvent(event: Int, path: String?) {
        when (event) {
            ACCESS -> Log.i("Zero", "ACCESS: $path") //文件被访问
            ATTRIB -> Log.i("Zero", "ATTRIB: $path")//文件属性被修改，如 chmod、chown、touch 等
            CLOSE_NOWRITE -> Log.i("Zero", "CLOSE_NOWRITE: $path")//不可写文件被 close
            CLOSE_WRITE -> Log.i("Zero", "CLOSE_WRITE: $path")//可写文件被 close
            CREATE -> Log.i("Zero", "CREATE: $path")//创建新文件
            DELETE -> Log.i("Zero", "DELETE: $path")// 文件被删除，如 rm
            DELETE_SELF -> Log.i("Zero", "DELETE_SELF: $path")// 自删除，即一个可执行文件在执行时删除自己
            MODIFY -> Log.i("Zero", "MODIFY: $path")//文件被修改
            MOVE_SELF -> Log.i("Zero", "MOVE_SELF: $path")//自移动，即一个可执行文件在执行时移动自己
            MOVED_FROM -> Log.i("Zero", "MOVED_FROM: $path")//文件被移走，如 mv
            MOVED_TO -> Log.i("Zero", "MOVED_TO: $path")//文件被移来，如 mv、cp
            OPEN -> Log.i("Zero", "OPEN: $path")//文件被 open
            else ->
                //CLOSE ： 文件被关闭，等同于(IN_CLOSE_WRITE | IN_CLOSE_NOWRITE)
                //ALL_EVENTS ： 包括上面的所有事件
                Log.i("Zero", "DEFAULT($event): $path")
        }
    }

}
