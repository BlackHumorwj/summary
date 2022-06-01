package com.kotlin;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author pikachu
 * @time 2016/7/26 9:58
 * @desc 作为一个枢纽连接观察者和被观察者
 */
public class RxBus {


    /**
     * 单利模式 注意：发送时的Bus对象必须和接受时的对象相同才可以
     */
    static RxBus instance;


    /**
     * 定义一个Subject 既是观察者也可以作为被观察者
     * SerializedSubject 是线程安全的
     */
    private final Subject<Object> mBus = PublishSubject.create().toSerialized();

    private final Map<Class<?>, Object> mStickyEventMap;


    private RxBus() {
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    /**
     * 单利获取实例
     *
     * @return RxBus对象
     */
    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 发送的信息
     *
     * @param o 数据
     */
    public void send(Object o) {
        mBus.onNext(o);
    }

    /**
     * 接受消息并处理消息
     * ofType 对信息进行筛选处理
     *
     * @return 被观察者
     */
    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    /**
     * Stciky 相关
     */

    /**
     * 发送一个新Sticky事件
     */
    public void sendSticky(Object event) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        send(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> observable = mBus.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);

            if (event != null) {
                return observable.mergeWith(Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                        emitter.onNext(Objects.requireNonNull(eventType.cast(event)));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    /**
     * 根据eventType获取Sticky事件
     */
    public <T> T getStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }


    public void unRegister(Disposable... disposable) {

        if (disposable == null) {
            return;
        }
        for (Disposable item : disposable) {
            if (item != null && !item.isDisposed()) {
                item.dispose();
            }
        }
    }

}
