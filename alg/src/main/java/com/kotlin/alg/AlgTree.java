package com.kotlin.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/17
 *     desc   : 二叉树的遍历
 *              深度遍历：
 *                  前序遍历
 *                  中序遍历
 *                  后序遍历
 *
 *              广度遍历
 *                  层级遍历
 *     version: 1.0
 * </pre>
 */

class AlgTree {

    static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int value;

        public TreeNode(TreeNode left, TreeNode right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    //前序遍历
    /*
     中 ，左，右
     */

    /**
     * 前序遍历
     *
     * @param node
     */
    public static void fun1(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value);

        fun1(node.left);

        fun1(node.right);

    }


    //中序遍历
    /*
    左 中 右
     */
    public static void fun2(TreeNode node) {
        if (node == null) {
            return;
        }
        fun1(node.left);
        System.out.print(node.value);
        fun1(node.right);

    }


    //后序遍历
    /*
    左 右 中
     */
    public static void fun3(TreeNode node) {
        if (node == null) {
            return;
        }
        fun1(node.left);
        fun1(node.right);
        System.out.print(node.value);
    }


    //层级遍历
    public static void fun4(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(node);

        while (!queue.isEmpty()){
            final TreeNode treeNode = queue.poll();
            System.out.print(treeNode.value);
            if (treeNode.left!=null){
                queue.add(treeNode.left);
            }
            if (treeNode.right!=null){
                queue.add(treeNode.right);
            }



        }
    }


}
