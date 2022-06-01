package com.kotlin.for2021.android.jetpack.coroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kongzue.dialog.v3.WaitDialog
import com.kotlin.for2021.BluetoothViewActivity
import com.kotlin.for2021.R

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class CoroutineDemo2Activity : AppCompatActivity() {


    private lateinit var tvName: TextView

    private val TAG = this::class.java.name

    private var viewMode: CoroutineDemoViewModel? = null


    companion object {
        fun newInstance(context: Context?): Intent {
            return Intent(context, CoroutineDemo2Activity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corouine)

        tvName = findViewById<TextView>(R.id.tv_name);

        //ViewMode实例
        viewMode = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CoroutineDemoViewModel() as T
            }
        }).get(CoroutineDemoViewModel::class.java)

        //获取数据
        viewMode?.getAppInfo()

        initView()

    }

    private fun initView() {

        //观察数据回调
        viewMode?.responseLv?.observe(this) {
            tvName.text = it.toString()
        }

        viewMode?.dialogStatus?.observe(this) {

            when (it) {
                0 -> {
                    WaitDialog.show(this, "加载中")
                }
                1 -> {
                    WaitDialog.dismiss()
                }
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        return super.onCreateOptionsMenu(menu)
    }


}