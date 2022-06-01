package com.kotlin.for2021.android.jetpack.flow

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.kotlin.for2021.R
import com.kotlin.for2021.android.jetpack.viewBinding.BaseViewBindingActivity
import com.kotlin.for2021.databinding.ActivityFlowBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private val Result.Companion.Success: Any
    get() {
        return "Loading"
    }
private val Result.Companion.Loading: Any
    get() {
        return "Loading"
     }

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class FlowActivity : BaseViewBindingActivity() {


    lateinit var binder: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        binder = ActivityFlowBinding.inflate(layoutInflater)
        test()
        testUiState()

        uiState.value = Result.Success

    }

    private fun test() {
        lifecycleScope.launch {
            testFlow.collect(action = {
                Log.e("testFlow", it)
            })
        }
    }


    //一般的Fow , 仅有一个观察者 。冷流 。
    private val testFlow = flow<String> {
        emit("hhh${Thread.currentThread().name}")
        emit("dss")
    }


    //StateFlow
    //有状态的Flow ，可以有多个观察者，热流
    //构造时需要传入初始值 : initialState
    //常用作与UI相关的数据观察，类比LiveData

    private val uiState = MutableStateFlow(Result.Loading)

    private fun testUiState(){

        lifecycleScope.launch {
            uiState.collect { value->
                println(value)
            }
        }
    }

    //SharedFlow
    //可定制化的StateFlow，可以有多个观察者，热流.
    //无需初始值，有三个可选参数：
    //replay - 重播给新订阅者的值的数量（不能为负，默认为零）。
    //extraBufferCapacity - 除了replay之外缓冲的值的数量。 当有剩余缓冲区空间时， emit不会挂起（可选，不能为负，默认为零）。
    //onBufferOverflow - 配置缓冲区溢出的操作（可选，默认为暂停尝试发出值）
    //使用SharedFlow 你可以写个 FlowEventBus

    val signEvent = MutableSharedFlow<String>(replay = 2)

    private fun singEventTest(){
        lifecycleScope.launch {
            signEvent.collect {
                println(it)
            }
        }
        signEvent.tryEmit("哈哈哈")
    }



}