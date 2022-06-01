package com.kotlin.design.cjx.factory;

import java.math.BigDecimal;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class NumFactoryImpl implements NumFactory {
    @Override
    public Number parse(String s) {
        return new BigDecimal(s);
    }
}
