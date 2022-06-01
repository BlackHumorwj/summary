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

class ObserverPatternDemo {


    public static void main(String[] args) {

        final Subject subject = new Subject();


        final Observer1 observer1 = new Observer1();
        subject.addObserver(observer1);

        final Observer2 observer2 = new Observer2();
        subject.addObserver(observer2);

        subject.setState(3);

        System.out.println("------------------------");

        //移除第二个观察者
        subject.removeObserver(observer2);
        subject.setState(9);

    }
}
