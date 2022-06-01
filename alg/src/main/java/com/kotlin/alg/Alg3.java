package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg3 {

    //这个题目说的是，给你一个整数数组，并且这个数组是按递增排序的，你要找到数组中的两个整数，它们的和等于给定的目标值，
    //然后返回它们的下标。题目假设给你的数组总是有且只有一个解，而且同一个元素不能使用两次。另外，返回结果的下标要从 1 开始。


    //双指针求解
    private int[] fun1(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {

            //判断右边最大值是否大于目标值，大于则左移
            if (arr[j] > target) {
                j--;
                continue;
            }

            //判断左边数和右边数之和是否小于目标值，小于则右移
            if (arr[i] + arr[j] < target) {
                i++;
                continue;
            }


            if (arr[i] + arr[j] == target) {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }

}
