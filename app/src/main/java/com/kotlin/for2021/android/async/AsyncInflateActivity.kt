package com.kotlin.for2021.android.async

import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.kotlin.for2021.R
import com.kotlin.for2021.android.BaseActivity

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class AsyncInflateActivity : BaseActivity() {


    override fun getLayoutResId(): Int {
        return -1;
    }

    override fun initView() {
        val asyncLayoutInflater = AsyncLayoutInflater(this).inflate(
            R.layout.activity_async_infalte,
            null
        ) { view, resid, parent -> setContentView(view) }

    }

}