package com.kotlin.design.xwx.observer;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface BaseObservable {


    void addObserver(MyObserver observer);

    void removeObserver(MyObserver observer);


    void notifyDataChanged();
}
