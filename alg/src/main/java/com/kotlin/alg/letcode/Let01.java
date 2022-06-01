package com.kotlin.alg.letcode;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/21
 *     desc   : 盛最多水的容器 https://leetcode-cn.com/problems/container-with-most-water/
 *     version: 1.0
 * </pre>
 */

public class Let01 {

    //
    //暴力解法
    /*
     双层for循环，计算最大面积
     */
    public static void fun1(int[] arr) {

        int totalArea = 0;

        //这一套代码要熟悉
        for (int i = 0; i < arr.length; i++) {
            //这一套代码要熟悉
            for (int j = i + 1; j < arr.length; j++) {

                int len = j - i;
                int height = Math.min(arr[j], arr[i]);

                totalArea = Math.max(totalArea, len * height);
            }
        }
        System.out.println("totalArea =" + totalArea);
    }


    //双指针解法
    /*
    指针移动规则，左右数值比较，数值小的移动。
    双指针法证明
     */
    public static void fun2(int[] arr) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        int maxArea = 0;

        while (leftIndex < rightIndex) {

            int len = rightIndex - leftIndex;
            int height = Math.min(arr[leftIndex], arr[rightIndex]);

            maxArea = Math.max(maxArea, height * len);

            //指针移动条件
            if (arr[leftIndex] < arr[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        System.out.println("maxArea =" + maxArea);

    }


}
