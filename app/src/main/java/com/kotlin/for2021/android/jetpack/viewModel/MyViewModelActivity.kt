package com.kotlin.for2021.android.jetpack.viewModel

import com.kotlin.for2021.android.BaseActivity
import com.kotlin.for2021.R
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * <pre>
 * author : ZW002
 * e-mail : jinbao@cash360.cn
 * time   : 2021/11/08
 * desc   :
 * version: 1.0
</pre> *
 */
class MyViewModelActivity : BaseActivity() {
    // onCreate ->onResume ->config change -> onRetainNonConfigurationInstance-> onDestroy -> onCreate()
    override fun initView() {}
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "哈哈哈"
        //        final ViewModelProvider viewModelProvider = new ViewModelProvider(this, new ViewModelProvider.Factory() {
        //
        //            @NonNull
        //            @Override
        //            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //                return (T) new MyViewModel();
        //            }
        //        });
        //
        //
        //        final ViewModelProvider modelProvider = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
        //
        //
        //        //ViewModelStore
        //        final MyViewModel viewModel = viewModelProvider.get(MyViewModel.class);
        //
        //
        //        final MyViewModel myViewModel = modelProvider.get(MyViewModel.class);
        //
        //        // viewModel.mLiveData.setValue("");
        //
        //        Log.e("xxx", "onCreate" + viewModel.toString());


        val viewModelProvider = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyViewModel() as T
            }
        })
        val viewModel = viewModelProvider.get(MyViewModel::class.java)

        viewModel.mLiveData.value = "12"



        lifecycle.addObserver(LifecycleEventObserver { source, event ->
            Log.e(TAG, event.name + "")
            if (event == Lifecycle.Event.ON_CREATE) {
            } else if (event == Lifecycle.Event.ON_START) {
            } else if (event == Lifecycle.Event.ON_RESUME) {
            } else if (event == Lifecycle.Event.ON_PAUSE) {
            } else if (event == Lifecycle.Event.ON_STOP) {
            } else if (event == Lifecycle.Event.ON_DESTROY) {
            }
        })
    }
}