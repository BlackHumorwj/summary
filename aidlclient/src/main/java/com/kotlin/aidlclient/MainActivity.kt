package com.kotlin.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.aidlservice.BlogInfo
import com.kotlin.aidlservice.IBlogManager

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/06/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class MainActivity : AppCompatActivity() {


    var blogManager: IBlogManager? = null
    var connected: Boolean? = false

    var serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            blogManager = IBlogManager.Stub.asInterface(service)
            connected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            connected = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_getData).setOnClickListener {
            if (connected == true) {
                Log.e("xxx", blogManager?.pullFromService().toString())
            }
        }

        findViewById<View>(R.id.btn_setData).setOnClickListener {
            if (connected == true) {
                 blogManager?.pushToService(BlogInfo().apply { name = "ddd" })
            }
        }

        bindService()

    }

    private fun bindService() {
        val intent = Intent() //绑定服务
        intent.setPackage("com.kotlin.aidlservice")
        intent.action = "android.intent.action.AidlService"
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (connected == true) {
            unbindService(serviceConnection)
        }
    }


}