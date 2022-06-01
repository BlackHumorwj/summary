package com.kotlin.for2021.android.laucherMode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.for2021.R

/**
 *
 *          author : ZW002
 *          e-mail : jinbao@cash360.cn
 *          time   : 2022/03/04
 *          desc   :
 *          version: 1.0
 *
 */

class FirstModeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_first_mode)

        findViewById<View>(R.id.btn).setOnClickListener {
            startActivity(Intent(this@FirstModeActivity, SecondModeActivity::class.java).also {
                it.putExtra("data", true)
            })
        }
        findViewById<View>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this@FirstModeActivity, ThreeModeActivity::class.java).also {
                it.putExtra("data", true)
            })
        }
    }


}
