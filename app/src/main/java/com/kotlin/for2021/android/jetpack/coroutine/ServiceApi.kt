package com.kotlin.for2021.android.jetpack.coroutine

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

interface ServiceApi {


    @GET("/global/version")
    fun getAppVersion(): Observable<AppVersion>




    @GET("/global/version")
    suspend fun getAppVersionCor() : AppVersion




}