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

class ChainClass {

    public static void main(String[] args) {
        Leader classAdviser = new ClassAdviser();
        Leader deptHead = new DeptHead();
        Leader dean = new Dean();


        classAdviser.setNext(deptHead);
        deptHead.setNext(dean);

        classAdviser.handleRequest(15);

    }

}

