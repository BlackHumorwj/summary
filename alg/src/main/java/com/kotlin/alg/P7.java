package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P7 {


    static int reverse(int n) {

        //Int.MaxValue =
        //Int.MinValue

        int result = 0;

        while (n != 0) {

            int m = n % 10;

            //反转后的数字进行判断

           //
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && m > 7)) {
                return 0;
            }


            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && m < -8)) {
                return 0;
            }


            n = n / 10;


            //fan zhuan hou de zhi
            result = result * 10 + m;
        }


        return result;

    }

    public static void main(String[] args) {
        System.out.println(reverse(2147483647));
    }

}
