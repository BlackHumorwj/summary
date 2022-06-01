package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/23
 *     desc   : P19. 合并两个有序数组
 *     version: 1.0
 * </pre>
 */

class Alg19 {

    //这个题目说的是，给你两个递增排序的数组，你要把第二个数组合并到第一个，并使其仍然保持递增排序。
    //两个数组中的元素个数会显式地给出，并且第一个数组的大小可以容纳下两个数组中所有的元素。

    /*

    数组是递增的

     定义指针，从右往左遍历

    int i = m1-1 ,j= m2-1,k= m1+m2-1
    //比较
    arr1[i] > arr2[j] => arr1[k] = arr1[i]
                         k--,i--

    arr1[i] < arr2[j] => arr1[k] = arr2[j]
                         k--,j--

    如果 j>=0 => 数组2 数据拷贝到数组1

     */

    /**
     *  arr2 合并到 arr1中
     * @param arr1
     * @param m1 数组中数据长度
     * @param arr2
     * @param m2 数组中数据长度
     */
    private void fun1(int[] arr1, int m1, int[] arr2, int m2) {
        int i = m1 - 1;
        int j = m2 - 1;
        int k = m1 + m2 - 1;

        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k] = arr1[i];
                k--;
                i--;
            } else {
                arr1[k] = arr2[j];
                k--;
                j--;
            }
        }

        //如果2数组不为空，则填充到数组1
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }


    }


}
