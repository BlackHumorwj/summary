package com.kotlin.for2021.view.recyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/10/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyRv extends RecyclerView {


    int height;


    public MyRv(@NonNull Context context) {
        super(context);
    }

    public MyRv(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

//    @Override
//    protected void onMeasure(int widthSpec, int heightSpec) {
        //        if (maxHeight==-1){
        //            super.onMeasure(widthSpec, heightSpec);
        //        }else {
        //            super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(200, MeasureSpec.AT_MOST));
        //        }
//        super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
//    }


    public void setMaxHeight(int i) {
        height = i;
        requestLayout();
    }


    float mStartX;
    float mStartY;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mStartX = ev.getRawX();
//                mStartY = ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float endY = ev.getRawY();
//                float endX = ev.getRawX();
//                float x = endX - mStartX;
//                float y = endY - mStartY;
//                /* 左右滑动不拦截,上下滑动拦截*/
//                if (Math.abs(y) > Math.abs(x)) {
//                    /* 已经在顶部了*/
//                    if (y > 0 && isScrollTop()) {
//                        getParent().requestDisallowInterceptTouchEvent(true);
//                    } else if (y < 0 && isScrollBottom()) {
//                        // 不能再上滑了 ========================
//                        getParent().requestDisallowInterceptTouchEvent(true);
//                    } else {
//                        getParent().requestDisallowInterceptTouchEvent(true);
//                    }
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//            default:
//                break;
//
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        Log.e("Rv正在滑动", "-dx =" + dx + "---dy =" + dy);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }


    //    @Override
    //    public boolean dispatchTouchEvent(MotionEvent ev) {
    //
    //        final float x = ev.getX();
    //        final float y = ev.getY();
    //
    //        final int top = getTop();
    //
    //        final int action = ev.getAction();
    //        switch (action) {
    //            case MotionEvent.ACTION_DOWN:
    //                getParent().requestDisallowInterceptTouchEvent(true);
    //
    //                break;
    //            case MotionEvent.ACTION_MOVE:
    //                //往上滑动
    //                final LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
    //                final int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
    //                final int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
    //                if (y - lastY < 0) {
    //                    final int itemCount = getAdapter().getItemCount();
    //                    //顶部
    //                    if (top > 0) {
    //                        if (isScrollBottom()) {
    //                            //底部
    //                            getParent().requestDisallowInterceptTouchEvent(false);
    //                        } else {
    //                            getParent().requestDisallowInterceptTouchEvent(true);
    //                        }
    //                    } else {
    //                        getParent().requestDisallowInterceptTouchEvent(false);
    //                    }
    //
    //                    final float rawY = ev.getRawY();
    //                    Log.d("MyRv up", "top = " + top + "rawY = " + rawY + "---y = " + y);
    //                } else if (y - lastY > 0) {
    //                    //往下滑动
    //                    if (isScrollTop()) {
    //                        getParent().requestDisallowInterceptTouchEvent(false);
    //                    } else {
    //                        getParent().requestDisallowInterceptTouchEvent(true);
    //                    }
    //                    Log.d("MyRv down", "top = " + top + "---lastVisibleItemPosition =" + lastVisibleItemPosition + "----firstVisibleItemPosition = " + firstVisibleItemPosition);
    //                }
    //                break;
    //            case MotionEvent.ACTION_UP:
    //            case MotionEvent.ACTION_CANCEL:
    //                //父层ViewGroup不要拦截点击事件
    //                getParent().requestDisallowInterceptTouchEvent(false);
    //                break;
    //        }
    //
    //        lastX = x;
    //        lastY = y;
    //        return super.dispatchTouchEvent(ev);
    //    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);

    }


    /**
     * 滑动到底部检查
     *
     * @return true滑动到底部，false没有到底
     */
    private boolean isScrollBottom() {
        return !canScrollVertically(1);
    }

    /**
     * 滑动到顶部检查
     *
     * @return true滑动到顶部，false没有到顶
     */
    private boolean isScrollTop() {
        return !canScrollVertically(-1);
    }

}
