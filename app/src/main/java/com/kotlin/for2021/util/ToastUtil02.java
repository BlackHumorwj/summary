package com.kotlin.for2021.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kotlin.for2021.R;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ToastUtil02 {

    public ToastUtil02(Context context) {
        ToastMsg.BUILDER.init(context.getApplicationContext());
    }

    public enum ToastMsg {
        BUILDER;

        private Toast toast;
        private TextView tv;
        private View view;

        private void init(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.toast, null);
            tv = (TextView) view.findViewById(R.id.tv_toast);
            toast = new Toast(context);
            toast.setView(view);
        }

        public void showToast(CharSequence text, int duration) {
            if (text.length() != 0) {
                tv.setText(text);
                toast.setDuration(duration);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        40);
                toast.show();
            }
        }

        public void showToast(int id, int duration) {
            if (id != 0) {
                tv.setText(id);
                toast.setDuration(duration);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        40);
                toast.show();
            }
        }
    }

    public void toast(String text) {
        ToastMsg.BUILDER.showToast(text, 0);
    }

    public void toast(String text, int duration) {
        ToastMsg.BUILDER.showToast(text, duration);
    }

    public void toast(int id) {
        ToastMsg.BUILDER.showToast(id, 0);
    }


}
