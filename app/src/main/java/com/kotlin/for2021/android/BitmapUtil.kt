package com.kotlin.for2021.android

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.Log
import com.kotlin.for2021.R
import kotlin.math.log

/**
 * <pre>
 * author : ZW002
 * e-mail : jinbao@cash360.cn
 * time   : 2022/03/01
 * desc   :
 * version: 1.0
</pre> *
 */
internal object BitmapUtil {


    fun init(context: Context) {

        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.puke_red_a)

        //代表存储Bitmap的色素需要的最少内存
        val allocationByteCount = bitmap.allocationByteCount

        //bitmap实际大小
        val byteCount = bitmap.byteCount

        Log.e("BitMapUtil", "allocationByteCount = $allocationByteCount byteCount =$byteCount")


        //nTargetDensity 当前系数密度
        //inDensity  图片所在文件夹对应的密度

        //加载一张本地资源图片，那么它占用的内存 = width * height * inTargetDensity/inDensity * inTargetDensity/inDensity * 一个像素所占的内存。


        val options = BitmapFactory.Options()
        options.inDensity = 320
        options.inTargetDensity = 320
        options.inSampleSize = 2

        BitmapFactory.decodeResource(context.resources, R.drawable.puke_red_a,options)

        //对图片的大小进行压缩，不会改变图片占用的内存大小
        //图片10mb压缩到 5mb
        //bitmap.compress()


        //图片的宽高缩小值 inSampleSize 倍
        //options.inSampleSize = 2


        //图片所在文件夹的
        val inDensity = DisplayMetrics.DENSITY_DEFAULT
        //手机的 densityDpi
        val inTargetDensity = context.resources.displayMetrics.densityDpi;

        Log.e("BitMapUtil", "inDensity = $inDensity inTargetDensity =$inTargetDensity")

        bitmap.recycle()

    }
}