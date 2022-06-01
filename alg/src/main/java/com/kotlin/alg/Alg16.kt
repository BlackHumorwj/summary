package com.kotlin.alg

import java.util.*

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/16
 *     desc   : 二叉树最小深度
 *     version: 1.0
 * </pre>
 */


class Alg16 {


    //递归求解
    /*
    1、根节点null -> 0
    2、left==null,right==null -> 这个层级是叶子节点 1
    3、left==null,right !=null -> f(right)+1
    4、left!=null,right==null -> f(left)+1
     */


    //层级遍历
    /*
    利用队列，存储层级里面的字段，然后遍历队列

    if(left==null&&right==null){
      return  deep
    }

    if(left!=null){
     list.add(node)
    }

     if(right!=null){
     list.add(node)
    }

    depth++

     */

//    public class TreeNode {
//        var left: TreeNode? = null
//        var right: TreeNode? = null
//        var value: Int = 0
//    }
//
//
//    //递归
//    fun m1(node: TreeNode?): Int {
//        if (node == null) {
//            return 0
//        }
//
//        if (node.left == null && node.right == null) {
//            return 1
//        }
//
//        if (node.left == null) {
//            m1(node.right) + 1
//        }
//
//        if (node.right == null) {
//            m1(node.left) + 1
//        }
//
//        return Math.min(m1(node.left), m1(node.right)) + 1
//
//    }
//
//    fun m2(node: TreeNode?): Int {
//        if (node == null) {
//            return 0
//        }
//        val queue = LinkedList<TreeNode>()
//        queue.add(node)
//
//        var depth: Int = 0
//
//        while (!queue.isEmpty()) {
//
//            for (i in 0 until queue.size) {
//
//                val pollNode = queue.poll()
//
//                if (pollNode.left == null && pollNode.right == null) {
//                    return depth
//                }
//
//                if (pollNode.left != null) queue.add(pollNode.left!!)
//                if (pollNode.right != null) queue.add(pollNode.right!!)
//            }
//
//            return ++depth
//
//
//        }
//
//        return -1
//
//
//    }


}