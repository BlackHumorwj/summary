package com.kotlin.for2021.android.jetpack.viewBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kotlin.for2021.databinding.ActivityViewBindingBinding

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class ViewBindingActivity : BaseViewBindingActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //辅助文件生成太多，影响包体积和方法数
        val binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvMobile.text = "hello"
        binding.tvName.text = "world"

    }


}