package com.kotlin.for2021

import android.content.Intent
import androidx.core.util.Consumer
import com.kotlin.RxBus
import io.reactivex.disposables.Disposable


fun Intent.sendExtData(obj: Any): Intent {
    RxBus.getInstance().sendSticky(obj)
    return this
}


fun <T> Intent.getExtData(obj: Class<T>, callBack: Consumer<T>): Disposable {
    return RxBus.getInstance().toObservableSticky(obj).subscribe() {
        callBack.accept(it)
        RxBus.getInstance().removeStickyEvent(obj)
    }
}

