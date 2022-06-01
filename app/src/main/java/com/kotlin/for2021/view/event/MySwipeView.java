package com.kotlin.for2021.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MySwipeView extends SwipeRefreshLayout {
    public MySwipeView(@NonNull Context context) {
        super(context);
    }

    public MySwipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    int lastX;
    int lastY;

    //滑动事件冲突，外部拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        final float y = ev.getY();
        final float x = ev.getX();
        boolean intercept = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;//DOWN 事件不能进行拦截
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(y - lastY) > Math.abs(x - lastX)) {
                    //垂直方向
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false; //UP事件不能进行拦截
                break;
        }
        this.lastX = (int) x;
        this.lastY = (int) y;
        return intercept;

        //return super.onInterceptTouchEvent(ev); 传递给下面子View
    }
    /*





     */


}
