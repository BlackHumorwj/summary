package com.kotlin.for2021.android.jetpack.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/11/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyViewModel extends ViewModel {

    public MutableLiveData<String> mLiveData = new MutableLiveData<>();


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
