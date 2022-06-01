package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/22
 *     desc   : 计算数的 n 次方
 *     version: 1.0
 * </pre>
 */

class P50 {


    public double fun(double value, int n) {
        double result = 1;
        // int 的最小值取反超出int的范围，所以要用long


        long N = Math.abs((long) n);

        for (int i = 0; i < N; i++) {

            result = result * value;

        }

        return n < 0 ? 1 / result : result;

    }

    public static double powFast(double x, int n) {
        double result = 1;
        long N = Math.abs((long) n);

        while (N != 0) {
            if ((N & 1) == 1) {
                result *= x;
            }
            x *= x;
            N >>= 1;
        }
        return n < 0 ? 1 / result : result;
    }

    public static void main(String[] args) {

        powFast(2,3);

    }


}
