package com.kotlin.for2021.android.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

/**
 * @author : kingBoy
 * @time 2020/12/17 20:17
 */
class LifecycleDemoActivity : AppCompatActivity() {

    //Lifecycle 源码解析
    //参考： https://blog.csdn.net/xx326664162/article/details/90749168


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var life: LifecycleRegistry = lifecycle as LifecycleRegistry

        //添加生命周期的绑定
        life.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun myOnCreate() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun myOnStart() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun myOnResume() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun myOnPause() {
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun myOnStop() {
            }


            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun myOnDestroy() {

            }

        })

        life.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {


            }

        })

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {


            }

        })



    }

}