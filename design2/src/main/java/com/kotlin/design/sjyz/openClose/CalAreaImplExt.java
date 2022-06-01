package com.kotlin.design.sjyz.openClose;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
//使用扩展
class CalAreaImplExt extends CalAreaImpl {

    private double π = 3.14;

    @Override
    public double calCircle(int r) {
        return r * r * π;
    }
}
