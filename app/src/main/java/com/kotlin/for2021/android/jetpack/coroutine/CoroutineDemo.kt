package com.kotlin.for2021.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class CoroutineDemo {


    fun request(id: Int, listener: ResponseListener) {
        Thread.sleep(30000)
        if (id % 2 == 0) {
            listener.success("成功")
        } else {
            listener.fail("失败")
        }
    }

    //将request 改造成suspend 方法
    suspend fun requestSuspend(id: Int): String = suspendCoroutine { continuation ->

        request(id, object : ResponseListener {
            override fun success(response: String) {
                continuation.resume(response)
            }

            override fun fail(fail: String) {
                continuation.resumeWithException(Exception(fail))
            }
        })

    }


    fun main(args: Array<String>) {

        GlobalScope.launch(Dispatchers.Main) {

//            val result = withContext(Dispatchers.IO) {
//                try {
//                    requestSuspend(12)
//                } catch (e: Exception) {
//                    e.message
//                }
//            }

            val result = getUserId()






            println(result)

        }


    }

    suspend fun getUserId(): String? {

//        return GlobalScope.async(Dispatchers.IO) {
//
//            val  result =  requestSuspend(12)
//
//
//            result
//
//        }.await()


        return withContext(Dispatchers.IO) {
            try {
                requestSuspend(12)
            } catch (e: Exception) {
                e.message
            }
        }
    }


}


interface ResponseListener {
    fun success(response: String)

    fun fail(fail: String)

}
