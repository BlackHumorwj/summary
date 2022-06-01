package com.kotlin.design.xwx.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/07
 *     desc   : 管理观察者
 *     version: 1.0
 * </pre>
 */

public class Subject implements BaseObservable {

    private List<MyObserver> mList = new ArrayList<>();

    private int state;

    public void addObserver(MyObserver observer) {
        mList.add(observer);
    }


    public void removeObserver(MyObserver observer) {
        mList.remove(observer);
    }

    @Override
    public void notifyDataChanged() {
        for (MyObserver observer : mList) {
            observer.update(state);
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyDataChanged();
    }



}
