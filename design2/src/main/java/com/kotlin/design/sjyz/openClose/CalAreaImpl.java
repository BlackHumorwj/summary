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

public class CalAreaImpl implements CalArea {

    private double π = 3.141592654;

    @Override
    public double calCircle(int r) {
        return r * r * π;
    }

    @Override
    public double calReg(int length, int width) {
        return length*width;
    }

    @Override
    public double calTriangle() {
        return 0;
    }
}
