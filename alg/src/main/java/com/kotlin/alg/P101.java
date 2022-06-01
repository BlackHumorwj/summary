package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P101 {

    //二叉树是否对称
    /*
    两个树互为镜像前提条件：
    1、节点值相等
    2、两个节点，节点1的左子树，节点2的右子数对称；节点1的右子树与节点2的左子树对称
     */


    public boolean fun1(AlgTree.TreeNode treeNode) {

        return checkNode(treeNode, treeNode);

    }

    private boolean checkNode(AlgTree.TreeNode p, AlgTree.TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.value == q.value && checkNode(p.left, q.right) && checkNode(p.right, q.left);

    }


}
