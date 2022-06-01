package com.kotlin.alg.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AdvancedSort {

    // merge_sort 归并排序

    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];

        mergeSort(arr, temp, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {

            int middle = (left + right) / 2;

            mergeSort(arr, temp, left, middle);

            mergeSort(arr, temp, middle + 1, right);

            merge(arr, temp, left, right, middle);
        }
    }

    /**
     * @param arr
     * @param temp   临时数组存放合并后数据
     * @param left
     * @param right
     * @param middle
     */
    private static void merge(int[] arr, int[] temp, int left, int right, int middle) {

        int i = left;

        int j = middle + 1;

        int k = 0; //临时数组索引

        while (i <= middle && j <= right) {

            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        //循环结束，两两比较合并结束，剩下元素直接赋值到临时数组
        while (i <= middle) {//
            temp[k++] = arr[i++];
        }

        while (j <= right) {//
            temp[k++] = arr[j++];
        }

        //临时数组拷贝到原数组
        for (int index = 0; index < k; index++) {
            //从什么位置开始
            arr[index + left] = temp[index];
        }

        // if (k >= 0)
        //            System.arraycopy(temp, 0, arr, 0 + left, k);
        // System.arraycopy(temp, 0, arr, left, k);

    }


    //---快速排序---------------------------------------------------

    //https://leetcode-cn.com/problems/sort-an-array/solution/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/

    public static int[] sortArray(int[] nums) {
        randomizedQuicksort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void randomizedQuicksort(int[] nums, int l, int r) {

         //递归终止条件
         if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomizedQuicksort(nums, l, pos - 1);
            randomizedQuicksort(nums, pos + 1, r);
        }
    }

    public static int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l; // 随机选一个作为我们的主元
        swap(nums, l, i);//pivot放数组最右边
        return partition(nums, l, r);
    }

    public static int partition(int[] nums, int l, int r) {
        int pivot = nums[l];//确定pivot值
        int pivotIndex = l;

        int index = pivotIndex + 1;

        for (int j = index; j <= r; ++j) { //从左往右遍历

            if (nums[j] < pivot) { //交换数值
                swap(nums, index, j);
                index++;
            }
        }
        swap(nums, index - 1, pivotIndex);
        return index - 1;
    }

    //    public int partition(int[] nums, int l, int r) {
    //        int pivot = nums[r];//确定pivot值
    //        int i = l - 1;
    //        for (int j = l; j <= r - 1; ++j) { //从左往右遍历
    //            if (nums[j] <= pivot) {
    //                i = i + 1;
    //                swap(nums, i, j);
    //            }
    //        }
    //        swap(nums, i + 1, r);
    //        return i + 1;
    //    }
    //

    private  static   void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, -12, 2, 78, -7, 12, 32, 85, 42, 125, 0};
        sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }


}
