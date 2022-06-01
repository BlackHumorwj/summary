package com.kotlin.alg;

import com.kotlin.alg.letcode.LinkNode;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/22
 *     desc   : 反转单链表
 *     version: 1.0
 * </pre>
 */

class P9 {

    /*
    1、双指针


     */

    public void fun(LinkNode linkNode) {

        //定义一个头结点
        LinkNode pre = null;
        //定义当前节点
        LinkNode cur = linkNode;

        while (cur != null) {
            //保存反转前的一个节点
            final LinkNode next = cur.next;

            //修改指针指向
            cur.next = pre;

            //移动两个指针
            pre = cur;

            cur = next;
        }


    }

}
