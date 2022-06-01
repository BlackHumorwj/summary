package com.kotlin.for2021.util

import android.content.Context
import android.widget.Toast
import com.kotlin.for2021.AppData

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class ToastUtil {


    companion object{

    private var toast : Toast? = null

        fun customToast(context: Context, tips : String){
             if (toast==null){
                 toast = Toast.makeText(context.applicationContext,tips,Toast.LENGTH_LONG)
             }else{
                 toast?.setText(tips)
             }
            toast?.show()
        }

        fun customToast2(context: Context, tips : String){
            Toast.makeText(context.applicationContext,tips,Toast.LENGTH_LONG)?.show()
        }

        fun customToast(  tips : String){
            Toast.makeText(AppData.context,tips,Toast.LENGTH_LONG)?.show()
        }


    }






}