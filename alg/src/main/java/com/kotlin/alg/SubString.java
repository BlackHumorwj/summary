package com.kotlin.alg;

import java.util.HashSet;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class SubString {


    public int longestSubString(String s) {
        int[] counts = new int[256];

        int i = 0, j = 0, maxLen = 0;
        for (; i < s.length(); i++) {

            for (; j < s.length(); j++) {

                if (counts[s.charAt(j)] != 0) {
                    //已经重复出现,结束循环
                    break;
                }
                counts[s.charAt(j)] += 1;
            }
            maxLen = Math.max(j - i, maxLen);
            counts[s.charAt(i)] -= 1;
        }
        return maxLen;
    }



    public static int longestSubString02(String src) {

        //使用HashSet存储数据 Character
        final HashSet<Character> set = new HashSet<>();


        int right = 0;
        int max = 0;

        //遍历src
        for (int i = 0; i < src.length(); i++) {


            if (i != 0) {
                //删除上一次遍历存入的起始数据
                set.remove(src.charAt(i - 1));

            }

            //右移 right 索引
            while (right < src.length() && !set.contains(src.charAt(right))) {

                set.add(src.charAt(right));

                right++;
            }

            //结束内层循环 有重复元素
            //子串长度确定 right - i
            max = Math.max(max, (right - i));


        }


        return max;

    }


    public static void main(String[] args) {

        System.out.println(longestSubString02("adabcdabcefg"));

    }

}
