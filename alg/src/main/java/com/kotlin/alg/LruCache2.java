package com.kotlin.alg;

import java.util.HashMap;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class LruCache2 {

    /*
    双向列表：记录顺序，常用的在表头，不常用的在尾节点

    hash表：取值时间复杂度 o(1)

     */

    private HashMap<Integer, LruNode> map = new HashMap<>();
    private int capacity;
    private LruNode head, tail;

    class LruNode {
        public int key;
        public int value;

        public LruNode pre;
        public LruNode next;

        public LruNode() {
        }

        public LruNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public LruCache2(int capacity) {
        this.capacity = capacity;
        //★★
        head = new LruNode();
        tail = new LruNode();
        //头尾节点建立关系
        head.next = tail;
        tail.pre = head;

    }

    public void put(int key, int value) {

        final LruNode node = map.get(key);
        if (node == null) {
            //put key 不存在
            //添加新值，并将双向量表对应的Node移到head
            LruNode lruNode = new LruNode(key, value);
            map.put(key, lruNode);
            //★★
            add2Head(lruNode);





            //容量超出
            //尾节点数据移除
            if (map.size() > capacity) {
                final LruNode tail = removeTail();
                map.remove(tail.key);
            }

        } else {
            //★★ put key 存在
            //覆盖原值，并将双向量表对应的Node移到head
            node.value = value;
            move2Head(node);

        }

    }

    /**
     * 删除尾节点
     */
    private LruNode removeTail() {
        //        tail = tail.pre;
        //        tail.next = null;
        final LruNode pre = tail.pre;
        removeNode(pre);
        return pre;
    }

    /**
     * 移动到头结点
     *
     * @param node
     */
    private void move2Head(LruNode node) {

        //★★解绑
        node.pre.next = node.next;
        node.next.pre = node.pre;

        //添加到头结点
        add2Head(node);
    }

    /**
     * ★★ 添加到头节点
     *
     * @param newNode
     */
    private void add2Head(LruNode newNode) {
        newNode.next = head.next;
        newNode.pre = head;

        head.next.pre = newNode;
        head.next = newNode;


    }


    private void removeNode(LruNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;

    }


    public int get(int key) {
        //是否存在
        if (!map.containsKey(key)) {
            return -1;
        }

        //存在，hash中返回，双向链表将元素移至表头
        final LruNode node = map.get(key);
        move2Head(node);
        return node.value;

    }


}
