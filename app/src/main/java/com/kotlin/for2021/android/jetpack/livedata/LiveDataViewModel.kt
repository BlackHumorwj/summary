package com.kotlin.for2021.android.jetpack.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author : kingBoy
 * @time 2020/12/17 19:23
 */
class LiveDataViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()



}