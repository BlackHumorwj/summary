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
//班主任
class ClassAdviser  extends Leader{
    @Override
    protected void handleRequest(int day) {
        if (day > 5) {
            if (getNext() != null) {
                getNext().handleRequest(day);
            }
        } else {
            System.out.println("我来处理"+this);
        }
    }
}
