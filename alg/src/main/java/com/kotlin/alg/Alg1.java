package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg1 {

    //这个题目说的是，给你一个字符串，你要判断它是否是回文字符串。字符串里只考虑字母和数字，其它的字符可以无视。另外，对于字母，可以忽略大小写。

    /**
     * @return true 字母数字的
     */

    private boolean isAlphanumeric(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;
    }


    private boolean isEqualIgnoreCase(char a, char b) {
        if (a >= 'A' && a <= 'Z')
            a += 32;
        if (b >= 'A' && b <= 'Z')
            b += 32;
        return a == b;
    }


    // 判断字符串是否有回文
    // Time o(n)  Space o(1)
    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        for (; i < j; ++i, --j) {
            //右值不是数字或者字符往左移动一位
            while (i < j && !isAlphanumeric(s.charAt(j))) {
                --j;
            }

            //左值不是数字或者字符往右移动一位
            while (i < j && !isAlphanumeric(s.charAt(i))) {
                ++i;
            }

            //两个值不相等返回 false
            if (i < j && !isEqualIgnoreCase(s.charAt(i), s.charAt(j))) {
                return false;
            }


        }

        return true;

    }

    public static void main(String[] args) {
        String str = "3233";
       System.out.println(isPalindrome1(str));

    }


    /**
     * for 循环判断字符串是否是回文
     * @param str
     * @return
     */
    private static boolean isPalindrome1(String str) {
        int len = str.length();//3
        //收尾字符是相等
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }


}
