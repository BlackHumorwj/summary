package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AlgUtil {

    public static class Node {
        public Node(int value) {
            this.value = value;
        }

        public Node() {

        }


        private int value;

        public Node next;


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    /**
     * 反转单向链表 ，采用双指针
     *
     * @param head 原链表
     * @return 反转后的链表
     */
    private Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        //双指针遍历
        Node pre = null;
        Node cur = head;

        while (cur.getNext() != null) {
            //获取下个值
            Node temp = cur.getNext();
            //当前值指向前一个
            cur.setNext(pre);

            //pre 往后移动一位
            pre = cur;
            //cur 往后移一位
            cur = temp;
        }

        return pre;//排序后的

    }


    private Node re1(Node head) {
        if (head == null) {
            return null;
        }

        Node pre = null, cur = head;

        while (head.next != null) {
            //保存下一个数据
            final Node next = cur.getNext();

            cur.next = pre;

            //pre ->
            pre = cur;

            //cur ->
            cur = next;


        }

        return pre;


    }

}
