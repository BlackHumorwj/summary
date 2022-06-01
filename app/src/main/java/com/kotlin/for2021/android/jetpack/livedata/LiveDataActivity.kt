package com.kotlin.for2021.android.jetpack.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * @author : kingBoy
 * @time 2020/12/17 19:21
 * @desc LiveData源码解析
 */
class LiveDataActivity : AppCompatActivity() {

    //参考 https://blog.csdn.net/xx326664162/article/details/90756817
    //    https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State?hl=zh-cn#STARTED

    private lateinit var viewModel: LiveDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.name.observe(this, Observer { value ->

        })

        //LifecycleRegistry

        viewModel.name.value = "ddddd"

        viewModel.name.postValue("aaaaa")


    }

}