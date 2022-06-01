package com.kotlin.for2021.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CanvasView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint = new Paint();
    private Paint mPaint2 = new Paint();

    public CanvasView(@NonNull Context context) {
        super(context);
    }

    public CanvasView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint2.setTextSize(29);
        mPaint2.setColor(Color.GREEN);


        mPaint.setTextSize(29);
        //文字相对于中心点，居中对齐 Paint.Align.RIGHT
        //文字居中对齐 Paint.Align.CENTER
        //文字居中对齐 Paint.Align.RIGHT
        mPaint.setTextAlign(Paint.Align.RIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawRect(0, 0, 100, 100, mPaint2);

        final Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //        fontMetrics.top;

        canvas.drawText("niuniuniu", getWidth() / 2, getHeight() / 2, mPaint);
    }
}
