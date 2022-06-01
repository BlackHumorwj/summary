package com.kotlin.for2021.view.recyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/10/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyScrollerView extends ScrollView {
    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//
//    @Override
//    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
//        child.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
//    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if (action==MotionEvent.ACTION_DOWN){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
