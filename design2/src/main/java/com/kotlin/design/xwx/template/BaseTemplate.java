package com.kotlin.design.xwx.template;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

abstract class BaseTemplate {

    /**
     * 模板方法
     * @param obj
     */
    public void invokeTemplateMethod(Object obj){
        //基本方法
        bindHeaderInfo();

        bindProductInfo();

        bindRemarkInfo();

        bindOtherInfo();

        bindOperateLogInfo();

    }

    protected abstract void bindOtherInfo();

    protected abstract void bindOperateLogInfo();

    protected abstract void bindRemarkInfo();

    protected abstract void bindProductInfo();

    protected abstract void bindHeaderInfo();

}
