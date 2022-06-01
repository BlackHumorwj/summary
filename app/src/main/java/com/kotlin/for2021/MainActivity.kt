package com.kotlin.for2021

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.os.Build
import android.view.ViewOutlineProvider
import android.graphics.Outline
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kongzue.dialog.v3.BottomMenu
import com.kotlin.alg.BubbleSort
import com.kotlin.for2021.android.BitmapUtil
import com.kotlin.for2021.android.LeakCanaryUtil
import com.kotlin.for2021.android.StrictModeUtil
import com.kotlin.for2021.android.animator.AnimatorActivity
import com.kotlin.for2021.android.async.AsyncInflateActivity
import com.kotlin.for2021.android.jetpack.coroutine.CoroutineDemoActivity
import com.kotlin.for2021.android.jetpack.flow.FlowActivity
import com.kotlin.for2021.android.jetpack.viewBinding.ViewBindingActivity
import com.kotlin.for2021.view.recyclerView.RvActivity
import com.kotlin.for2021.view.recyclerView.NestedRvActivity
import com.kotlin.for2021.view.viewPager.ViewPagerTestActivity
import com.kotlin.for2021.android.jetpack.viewModel.MyViewModelActivity
import com.kotlin.for2021.android.laucherMode.FirstModeActivity
import com.kotlin.for2021.android.virtual.VirtualActivity
import com.kotlin.for2021.apm.LayoutInflaterFactory2
import com.kotlin.for2021.base.HookUtil
import com.kotlin.for2021.camera.Camera01Activity
import com.kotlin.for2021.data.CommonData
import com.kotlin.for2021.dataStru.MapUtil
import com.kotlin.for2021.java.annotion.AnnotationActivity
import com.kotlin.for2021.util.ToastUtil
import com.kotlin.for2021.util.ToastUtil02
import com.kotlin.for2021.view.DragBubbleActivity
import com.kotlin.for2021.view.draw.DrawActivity
import com.kotlin.for2021.view.event.EventTest02Activity
import com.kotlin.for2021.view.event.EventTest03Activity
import com.kotlin.for2021.view.event.ViewTest01Activity
import com.kotlin.for2021.view.measure.MeasureActivity
import java.util.*
import kotlin.collections.ArrayList

/**
 *     author : jinBao
 *     time   : 2022/3/4  13:56
 *     desc   :
 *
 */
class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null
    private val mList: MutableList<CharSequence> = ArrayList()
    var classList: MutableList<CommonData> = ArrayList();


    override fun onCreate(savedInstanceState: Bundle?) {
        // 注意需在调用super.onCreate(savedInstanceState);之前设置
        LayoutInflaterCompat.setFactory2(layoutInflater, LayoutInflaterFactory2(delegate))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        initView()

        initData()
       // initUtil()

        // Logcat  Displayed 打印时间
      //  reportFullyDrawn()

    }

    private fun initView() {
        val tv1 = findViewById<TextView>(R.id.tv1)
        if (Build.VERSION.SDK_INT > 20) {
            tv1.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRect(0, 0, view.width + 20, view.height + 20)
                }
            }
            tv1.clipToOutline = true

        }

        findViewById<View>(R.id.some_id).setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivityForResult(intent, -1)
        }
        findViewById<View>(R.id.myview).setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {
                BottomMenu.show(this@MainActivity, list) { text, index ->
                    try {
                        var data = classList.get(index)
                        val name = Class.forName(data.patch)
                        startActivity(Intent(this@MainActivity, name))
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }

            private val list: List<CharSequence>
                private get() {
                    mList.clear()
                    classList.forEach {
                        mList.add(it.name)
                    }
                    return mList
                }

        })


        val mToastUtils = ToastUtil02(this);

        findViewById<TextView>(R.id.tv_toast).setOnClickListener {
            ToastUtil.customToast(this@MainActivity, "hhhhh")
        }



        findViewById<TextView>(R.id.tv_toast1).setOnClickListener {
            // ToastUtil.customToast2(this@MainActivity, "hhhhh")
            mToastUtils.toast("toast");  //默认的持续的时间是0
            mToastUtils.toast("toast", 1);
        }


    }

    private fun initUtil() {


        var srt: String = ""

        srt.haha()


        val arr = intArrayOf(1, 12, 2, 78, 7, 12, 32, 85, 42, 125)
//        BubbleSort.bubbleSort2(arr)
        BubbleSort.selectSort(arr);

        println(arr.contentToString())

        MapUtil.init()
        BitmapUtil.init(this)
        LeakCanaryUtil.fn()

        HookUtil.hook(this, findViewById<View>(R.id.some_id))

        StrictModeUtil.f1()


    }

    private fun initData() {
        classList.add(CommonData("协成", CoroutineDemoActivity::class.java.name))
        classList.add(CommonData("RecyclerView", RvActivity::class.java.name))
        classList.add(CommonData("NestedRvActivity", NestedRvActivity::class.java.name))
        classList.add(CommonData("ViewPager", ViewPagerTestActivity::class.java.name))
        classList.add(CommonData("ViewModel", MyViewModelActivity::class.java.name))
        classList.add(CommonData("Draw", DrawActivity::class.java.name))
        classList.add(CommonData("事件分发", EventTest02Activity::class.java.name))
        classList.add(CommonData("View滑动", ViewTest01Activity::class.java.name))
        classList.add(CommonData("事件处理", EventTest03Activity::class.java.name))
        classList.add(CommonData("View的测量", MeasureActivity::class.java.name))
        classList.add(CommonData("DragBubble", DragBubbleActivity::class.java.name))

        classList.add(CommonData("启动模式", FirstModeActivity::class.java.name))
        classList.add(CommonData("属性动画", AnimatorActivity::class.java.name))
        classList.add(CommonData("AsyncInflate", AsyncInflateActivity::class.java.name))
        classList.add(CommonData("ViewBinding", ViewBindingActivity::class.java.name))
        classList.add(CommonData("生物识别", VirtualActivity::class.java.name))
        classList.add(CommonData("相册【权限】", Camera01Activity::class.java.name))
        classList.add(CommonData("注解", AnnotationActivity::class.java.name))
        classList.add(CommonData("Flow", FlowActivity::class.java.name))
    }

    fun String.haha(): String {

        System.out.println(this.hashCode())

        return this
    }


    val TAG = javaClass.name


    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    /**
     * Activity 真正可见
     * @param hasFocus Boolean
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e(TAG, " $hasFocus")
    }


}