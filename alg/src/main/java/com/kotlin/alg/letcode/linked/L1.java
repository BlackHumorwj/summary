package com.kotlin.alg.letcode.linked;

import com.kotlin.alg.letcode.LinkNode;

import java.util.HashSet;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class L1 {

    //反转链表  https://leetcode-cn.com/problems/reverse-linked-list/solution/
    //迭代法
    public static LinkNode reverse(LinkNode head) {

        LinkNode pre = null;
        LinkNode curr = head;

        while (curr != null) {

            //1、保存curr 下一个值 next值
            final LinkNode next = curr.next;

            //2、变更当前节点方向 current next值为 pre
            curr.next = pre;

            //3、移动pre 和 current 到下一个节点
            pre = curr;
            curr = next;

        }
        return pre;//返回反转后的头节点
    }


    //141. Linked List Cycle 链表是否有环
    //https://leetcode.com/problems/linked-list-cycle/

    /*
    1、set进行判断

    2、快慢指针
     */
    // 1、set进行判断
    public static boolean hasCycle(LinkNode head) {
        final HashSet<LinkNode> nodes = new HashSet<>();
        while (head != null) {
            //元素已存在 add 方法返回false
            if (!nodes.add(head)) {
                return true;
            } else {
                nodes.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //2、快慢指针
    //正确性如何验证
    public static boolean hasCycle01(LinkNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        LinkNode slow = head;

        LinkNode fast = head.next;

        //结束条件 ！！！
        while (slow != fast) {
            //结束条件
            if (slow == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;


    }


}
