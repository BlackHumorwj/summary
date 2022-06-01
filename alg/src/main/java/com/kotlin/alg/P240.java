package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P240 {


    //从二维数组右上角位置开始往左和下遍历
    /*
    当前位置的值 < target =》 往下+1

    当前位置的值 > target => 往左 -1

     */

    public static int[] fun(int[][] arr, int target) {
        //特殊情况处理
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            return new int[]{-1, -1};
        }


        int length = arr[0].length;
        int high = arr.length;

        //定义起始点位置,双指针
        int i = 0;
        int j = high - 1;//

        while (i < length && j >= 0) {

            if (arr[i][j] > target) {
                j--;
            } else if (arr[i][j] < target) {
                i++;
            } else {
                return new int[]{i, j};
            }
        }

        return new int[]{-1, -1};


    }

    public static void main(String[] args) {


    }

}
