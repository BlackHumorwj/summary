package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg4 {
    // 这个题目说的是，给你一个二叉树，你要判断它是否沿中轴线对称。
    // 考察对象：树、深度优先，广度优先遍历




    /**
     * 左右树是否对称
     *
     * @param left
     * @param right
     * @return
     */
    private boolean isSymmetric(NodeTree left, NodeTree right) {
        if (left != null && right != null) {
            //判断数的值是否相等 && 左左 节点 右右 比较 && 左右 节点 右左比较
            return (left.value == right.value && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left));
        }
        return (left == null && right == null);
    }

    //m1 递归判断 是否对称  T:O(n) S:O(n)
    public boolean isSymmetricTreeRecursive(NodeTree tree) {
        if (tree == null) {
            return true;
        }
        return isSymmetric(tree.left, tree.right);
    }


    //m2 迭代方法 ???


}
