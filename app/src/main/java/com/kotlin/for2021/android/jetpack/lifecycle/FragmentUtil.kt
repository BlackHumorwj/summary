package com.kotlin.for2021.android.jetpack.lifecycle

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.kotlin.for2021.R

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/10/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class FragmentUtil {


    fun init(fragment: Fragment, activity: AppCompatActivity) {

        val beginTransaction = activity.supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_content, fragment)

       //----从Fragment onCreate()开始----------------------------------------------------------

        beginTransaction.commit()
        //生命周期 onCreate() onCreateView() onViewCreated onStart onResume


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.CREATED)
        //生命周期 onCreate()


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
        //生命周期 onCreate() onCreateView() onViewCreated onStart


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
        //生命周期 onCreate() onCreateView() onViewCreated onStart onResume

        //--------------------------------------------------------------

        //Fragment onResume() 开始


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.CREATED)
        //生命周期倒推  onResume() onPause() onStop() onDestroyView()


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
        //生命周期倒推  onResume() onPause()


        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.CREATED)
        //生命周期倒推  onResume() onPause() onStop() onDestroyView()
        beginTransaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
        //生命周期正推  onCreateView() onViewCreated() onStart()



        //通过setMaxLifecycle() 控制Fragment的生命周期 add attach方式

        //懒加载更容易实现



    }


}