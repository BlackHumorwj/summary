package com.kotlin.alg;

import com.kotlin.alg.letcode.Let01;
import com.kotlin.alg.letcode.Let02;
import com.kotlin.alg.letcode.Let03;
import com.kotlin.alg.sort.AdvancedSort;
import com.kotlin.alg.sort.SimpleSort;

import java.util.Arrays;

public class MyClass {

    public static void main(String[] args) {

         int[] arr = {1, -12, 2, 78, -7, 12, 32, 85, 42, 125, 0};

        // System.out.println(Arrays.toString(arr));


        AlgTree.fun1(initTree());
        System.out.println("---------------------------");
        AlgTree.fun2(initTree());
        System.out.println("---------------------------");
        AlgTree.fun3(initTree());
        System.out.println("---------------------------");
        AlgTree.fun4(initTree());
        System.out.println("---------------------------");
        Let01.fun1(arr);
        System.out.println("---------------------------");
        Let01.fun2(arr);
        System.out.println("---------------------------");
        Let02.fun1(4);
        System.out.println("---------------------------");
        Let03.threeSum(arr);
        System.out.println("---------------------------");
        SimpleSort.sort3(arr);
        System.out.println("---------------------------");
        AdvancedSort.sort(arr);
        System.out.println("---------------------------");
        System.out.println( com.kotlin.alg.let03.Let03.climbStairs(3));
        System.out.println("---------------------------");
        System.out.println(Arrays.toString(com.kotlin.alg.let03.Let03.insertionSort(arr)));

    }

    private static AlgTree.TreeNode initTree() {

        final AlgTree.TreeNode node5 = new AlgTree.TreeNode(null, null, 5);
        final AlgTree.TreeNode node4 = new AlgTree.TreeNode(null, null, 4);
        final AlgTree.TreeNode node3 = new AlgTree.TreeNode(null, null, 3);


        final AlgTree.TreeNode node2 = new AlgTree.TreeNode(node4, node5, 2);


        final AlgTree.TreeNode node1 = new AlgTree.TreeNode(node2, node3, 1);
        return node1;


    }


}