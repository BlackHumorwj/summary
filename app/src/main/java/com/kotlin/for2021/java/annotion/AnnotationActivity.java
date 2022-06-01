package com.kotlin.for2021.java.annotion;

import android.util.Log;
import android.widget.TextView;

import com.kotlin.for2021.R;
import com.kotlin.for2021.android.BaseActivity;
import com.kotlin.for2021.util.ToastUtil;

import java.lang.reflect.Field;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@Report(value = "nam", type = 1, level = "10")
public class AnnotationActivity extends BaseActivity {

    @Range(tips = "姓名")
    TextView mTextView;


    @Override
    public void initView() {
        getClassAnnotation();
        try {
            getFieldAnnotation(getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param clazz
     * @throws IllegalAccessException
     */
    private void getFieldAnnotation(Class<?> clazz) throws IllegalAccessException {

        if (clazz == null) {
            return;
        }
        final Field[] fields = clazz.getDeclaredFields();
        // this.getClass().getDeclaredFields()
        for (Field f : fields) {

            f.setAccessible(true);

            Log.e("xxx", f.getName() + "---" + f.getType());

            final boolean present = f.isAnnotationPresent(Range.class);
            if (present) {
                final Range range = f.getAnnotation(Range.class);

                // 获取Field的值:
                final Object fieldObj = f.get(this);
                if (fieldObj == null) {
                    if (range != null) {
                        ToastUtil.Companion.customToast2(this, range.tips() + "不能为空");
                    }
                }
            }
        }

        //
        final Class<?> superclass = clazz.getSuperclass();
        getFieldAnnotation(superclass);

    }

    private void getClassAnnotation() {
        TextView tvName = findViewById(R.id.tv_name);
        //获取类上注解
        //是否存在注解
        final boolean present = getClass().isAnnotationPresent(Report.class);
        if (present) {
            //拿到注解
            final Report report = getClass().getAnnotation(Report.class);
            if (report != null) {
                tvName.setText(report.level() + "--" + report.value());
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_annotation;
    }
}
