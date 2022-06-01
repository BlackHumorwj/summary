package com.kotlin.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P100 {


    //广度优先 采用队列

    public boolean isSameTree(AlgTree.TreeNode p, AlgTree.TreeNode q) {
        if(p==null&&q==null){
            return true;
        }

        //广度优先 采用队列
        Queue<AlgTree.TreeNode> q1 = new LinkedList<>();
        Queue<AlgTree.TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);

        while (!q1.isEmpty()&&!q2.isEmpty()){

            //取出栈顶元素
            final AlgTree.TreeNode node1 = q1.poll();
            final AlgTree.TreeNode node2 = q2.poll();

            //值不等 一定不等
             if (node1.value!=node2.value){
                return false;
             }


             //异或运算 ^  不同时为true和false 返回  true
             if (node1.left==null ^ node2.left==null){
                 return false;
             }

             if (node1.right==null^ node2.right==null){
                 return false;
             }

             //按顺序 存入队列
            if (node1.left!=null){
                q1.offer(node1.left);
            }

            if (node1.right!=null){
                q1.offer(node1.right);
            }

            if (node2.left!=null){
                q2.offer(node2.left);
            }

            if (node2.right!=null){
                q2.offer(node2.right);
            }
        }
        return q1.isEmpty() && q2.isEmpty();
    }


}
