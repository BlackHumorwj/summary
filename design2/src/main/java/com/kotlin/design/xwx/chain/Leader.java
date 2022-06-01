package com.kotlin.design.xwx.chain;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class Leader {
  protected Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }
    /**
     * 处理请求
     */
    protected abstract void handleRequest(int day) ;
}
