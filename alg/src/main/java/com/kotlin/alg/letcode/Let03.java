package com.kotlin.alg.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/21
 *     desc   : 15. 三数之和
 *     https://leetcode-cn.com/problems/3sum/
 *     version: 1.0
 * </pre>
 */

public class Let03 {


    //1、暴力解法 三重循环 n3
    public static List<List<Integer>> threeSum(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {

            for (int j = i + 1; j < nums.length - 1; j++) {

                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        System.out.println(" i=" + nums[i] + " j= " + nums[j] + " k = " + nums[k]);
                    }
                }
            }

        }
        return null;
    }
    //2、hash表记录 a,b a+b =-c


    //3、左右下标 ，证明是否正确
    /*
    排序
    1、固定一个 k 索引值
    if(arr[k]+arr[i]+arr[j]<0){
      i++; //增大值
    }else if(arr[k]+arr[i]+arr[j]>0){
      j-- //减小值
    }

    2、第一个数大于0，后面数都大于0

    3、去掉重复的可能

     */

    public static List<List<Integer>> threeSum01(int[] arr) {

        List<List<Integer>> list = new ArrayList<>();

        //1、排序
        Arrays.sort(arr);
        //
        for (int k = 0; k < arr.length; k++) {

            if (arr[k] > 0) {
                break;//2、退出循环，所有值都大于0
            }

            if (k > 0 && arr[k] == arr[k - 1]) {
                continue;//3、去掉重复情况
            }

            int target = -arr[k];

            //4、确定左右索引，使用双指针进行移动
            int left = k + 1;
            int right = arr.length - 1;

            while (left < right) {

                if (arr[left] + arr[right] == target) {//确定一组数据

                    list.add(new ArrayList<>(Arrays.asList(arr[k], arr[left], arr[right])));

                    //5、增加left 减小 right  并且不能重复
                    left++;
                    right--;

                    //6、移动后索引对应的值是否与前一个相同，判断重复情况
                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }

                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }


                } else if (arr[left] + arr[right] < target) {
                    //7，小于目标值，左指针右移
                    left++;
                } else {
                    //8、大于目标值，右指针左移
                    right--;
                }
            }
        }
        return list;
    }


    //两数之和
    /*
    1、暴力
    2、Hash表
     */


}
