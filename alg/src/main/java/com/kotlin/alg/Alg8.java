package com.kotlin.alg;

import java.util.Stack;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg8 {

    //这个题目说的是，给你两个二叉树，你要判断它们是否相同。这里所谓相同，指的是两棵树结构相同，并且相应节点上的数值相等。


    //递归
    private boolean recursive(NodeTree n1, NodeTree n2) {

        //终止条件
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 == null || n2 == null) {
            return false;
        }

        return n1.value == n2.value && recursive(n1.left, n2.left) && recursive(n1.right, n2.right);
    }


    //迭代
    private boolean iterative(NodeTree n1, NodeTree n2) {
        Stack<NodeTree> stack = new Stack<>();
        stack.add(n1);
        stack.add(n2);
        while (!stack.isEmpty()) {

            final NodeTree p1 = stack.pop();
            final NodeTree p2 = stack.pop();
            if (p1 == null && p2 == null) {
                continue;
            }

            if (p2 == null || p1 == null) {
                return false;
            }

            if (p1.value != p2.value) {
                return false;
            }
            stack.push(p1.left);
            stack.push(p2.left);
            stack.push(p1.right);
            stack.push(p2.right);
        }
        return true;
    }


}
