package com.kotlin.design.xwx.observer;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Observer2 extends MyObserver {

    @Override
    void update(int state) {
        System.out.println(getClass().getName() + "--" + state);
    }
}
