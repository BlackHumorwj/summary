package com.kotlin.for2021.android.jetpack.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class CoroutineDemoViewModel : ViewModel() {


    var serviceApi: ServiceApi = RetrofitUtil.instance.create(ServiceApi::class.java)

    var responseLv = MutableLiveData<AppVersion>()


    var dialogStatus: MutableLiveData<Int> = MutableLiveData()
    var viewLoadStatus: MutableLiveData<Int> = MutableLiveData()


    fun getAppInfo() {
        dialogStatus.value = 0
        viewModelScope.launch {

            //异步
            withContext(Dispatchers.IO) {
                delay(1000)
            }


            val response = serviceApi.getAppVersionCor()
            responseLv.value = response
            dialogStatus.value = 1
        }

    }


}