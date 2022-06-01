package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/13
 *     desc   : 冒泡排序
 *     version: 1.0
 *
 *     <a href="https://blog.csdn.net/pange1991/article/details/85460755">Link text</a>
 *
 * </pre>
 */

public class BubbleSort {

    /**
     * 冒泡排序从小到大排序：一开始交换的区间为0~N-1，将第1个数和第2个数进行比较，前面大于后面，交换两个数，否则不交换。再比较第2个数和第三个数，
     * 前面大于后面，交换两个数否则不交换。依次进行，最大的数会放在数组最后的位置。然后将范围变为0~N-2，数组第二大的数会放在数组倒数第二的位置。
     * 依次进行整个交换过程，最后范围只剩一个数时数组即为有序。
     */
    public static void bubbleSort2(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            //第一个元素开始 与第二个元素之后的所有元素进行比较

            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[i] > arr[j]) {
                    //交换索引下的值顺序
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    /**
     * 选择排序：从 0 到 n-1 选择一个最小的放 0的位置；从 1~n-1 选择一个最小的放 1 的位置；以此类推最后一个放 n-1位置
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            //最小确定的索引
            int minIndex = i;

            for (int j = i; j < arr.length; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                //交换两个索引的值,最小数值的索引与指定索引位置的值进行对换
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;

            }


        }


    }


}
