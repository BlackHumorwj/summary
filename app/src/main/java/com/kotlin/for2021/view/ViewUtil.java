package com.kotlin.for2021.view;

import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ViewUtil {


    private void init(View view) {

        final int top = view.getTop();


        MotionEvent motionEvent = null;


        //触摸点相对于View的坐标系位置
        final float y = motionEvent.getY();

        //触摸点相对于屏幕坐标系的位置
        final float rawX = motionEvent.getRawX();


    }


    private void fun1(View view){

        view.invalidate();



        view.post(new Runnable() {
            @Override
            public void run() {

            }
        });


    }



}
