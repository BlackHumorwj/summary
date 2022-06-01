package com.kotlin.for2021.android.jetpack.coroutine

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kotlin.for2021.R
import com.kotlin.for2021.databinding.ActivityCorouineBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class CoroutineDemoActivity : AppCompatActivity() {


    lateinit var serviceApi: ServiceApi
    lateinit var binding: ActivityCorouineBinding


    val TAG = this::class.java.name


    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCorouineBinding.inflate(layoutInflater)
        setContentView(binding.root)


        serviceApi = RetrofitUtil.instance.create(ServiceApi::class.java)

        requestData()
        // requestData1()

        initEvent()
    }

    private fun initEvent() {


        binding.btnClick.setOnClickListener {

            startActivity(CoroutineDemo2Activity.newInstance(this@CoroutineDemoActivity))

        }

    }


    //1、 比较基本使用
    fun requestData1() {
        //RxJava 处理并发
        val subscribe = serviceApi.getAppVersion()//
            .subscribeOn(Schedulers.io())//
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.tvName.text = it.toString()

            }, {
                it.printStackTrace()
            }, {

            })
    }

    private fun requestData() {

        // Dispatchers.Main 协程调度器为主线程
        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "GlobalScope = " + Thread.currentThread().name)
            val data = serviceApi.getAppVersionCor()
            binding.tvName.text = data.toString()

        }

        lifecycleScope.launch {

            Log.e(TAG, "lifecycleScope = " + Thread.currentThread().name)

            val appVersion = serviceApi.getAppVersion()

        }


    }

    fun requestData2() {
        // Dispatchers.Main 协程调度器为主线程
        //协程上下文[协程调度器 其实也是 协程上下文]、协程启动模式、协程体
        /*
        CoroutineScope.launch(
        context: CoroutineContext = EmptyCoroutineContext, // 协程上下文[协程调度器 其实也是 协程上下文]
        start: CoroutineStart = CoroutineStart.DEFAULT,//协程启动模式
        block: suspend CoroutineScope.() -> Unit //协程体
        )
         */


        job = GlobalScope.launch(Dispatchers.Main) {
            val data = serviceApi.getAppVersionCor()
            // 再使用async 并发请求第一个和第二个新闻的详情
            var v1 = async { serviceApi.getAppVersionCor() }
            var v2 = async { serviceApi.getAppVersion() }
            binding.tvName.text = data.toString()

        }
    }

    //顶级协成 GlobalScope 要手动销毁
    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        mainScope.cancel()
    }


    // 2 MainScope 替代 GlobalScope
    private val mainScope = MainScope()

    fun f1() {
        mainScope.launch {
            val appVersionData = serviceApi.getAppVersionCor()

        }
    }

    //3 lifeCycle对协成支持
    //implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.2.4"

    fun f2() {

        //lifecycleScope.launchWhenCreated {  }
        // lifecycleScope.launchWhenStarted {  }
        // lifecycleScope.launchWhenResumed {  }


    }

    //


}