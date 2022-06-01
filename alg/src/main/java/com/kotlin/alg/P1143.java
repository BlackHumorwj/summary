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

class P1143 {


    static int longestSubSeq(String text1, String text2) {

        //采用二维数组进行处理数组的长度arr[n+1][m+1]
        //画图总结规律，推算出DP方程
        //text1[i-1] 等于 text2[j-1] =》 dp[i,j] = dp[i-1,j-1]+1
        //text1[i-1] 不相等 text2[j-1] =》dp[i,j] = Max(dp[i-1,j],dp[i,j-1])

        //定义数组长度
        int n = text1.length();
        int m = text2.length();

        //其中  dp[i][j] 表示 text1[0:i],text2[0:j]

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {

            final char c = text1.charAt(i);
            for (int j = 1; j <= m; j++) {
                final char c1 = text2.charAt(j);
                if (c == c1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];

    }

    public static void main(String[] args) {

    }

}
