package com.kotlin.alg.letcode02;

import com.kotlin.alg.letcode.LinkNode;

import java.util.HashSet;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class LinkedDemo {


    //链表反转
    private LinkNode reverseList(LinkNode head) {
        //
        LinkNode pre = null;
        LinkNode curr = head;

        //结束条件
        while (curr != null) {

            //
            final LinkNode next = curr.next;

            //移动指针
            curr.next = pre;


            pre = curr;
            curr = next;

        }

        //返回值问题
        return pre;

    }


    //链表是否有环

    /*
    借助 HashSet 元素唯一
     */
    private boolean hasCycle(LinkNode head) {

        final HashSet<LinkNode> nodes = new HashSet<>();

        while (head != null) {
            if (!nodes.add(head)) {
                return true;
            } else {
                nodes.add(head);
            }
            head = head.next;

        }

        return false;
    }

    /*
    快慢指针 有疑问 要复习
     */

    private boolean hasCycle02(LinkNode node) {

        LinkNode slow = node;
        LinkNode fast = node.next;

        //01 结束条件 两个对象是否重逢
        while (slow != fast) {

            // 02 内部结束条件
            if (slow.next == null || fast.next == null) {
                return false;
            }

            slow = slow.next;

            fast = fast.next.next;
        }

        return true;


    }


}
