package com.kotlin.for2021.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.for2021.java.annotion.Range

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

abstract class BaseActivity : AppCompatActivity() {

   val TAG= javaClass.name

    @Range(tips = "product")
    var mTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayoutResId()!=-1){
            setContentView(getLayoutResId())
        }
        initView()
    }

    abstract fun initView()

    protected abstract fun getLayoutResId(): Int
}