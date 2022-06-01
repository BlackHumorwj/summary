package com.kotlin.for2021.android.jetpack.coroutine

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class RetrofitUtil {

    var retrofit: Retrofit? = null


    fun getInstance() {

        val client = OkHttpClient.Builder()
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.X509Trust())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .addInterceptor(HttpLoggingInterceptor() {
                Log.d("CoroutineDemoActivity", it)
            })
            .build()

        retrofit = Retrofit.Builder().baseUrl("https://uatapi.boss361.cn/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }


    companion object{

        val instance: Retrofit by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {

            val client = OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.X509Trust())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(HttpLoggingInterceptor() {
                    Log.d("CoroutineDemoActivity", it)
                })
                .build()

              Retrofit.Builder().baseUrl("https://uatapi.boss361.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

    }


}