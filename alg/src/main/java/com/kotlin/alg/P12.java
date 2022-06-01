package com.kotlin.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P12 {

    private static Integer[] intArr = new Integer[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    //1 将特殊的字符也写入数组，从大到小
    private static String[] StringArr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRuoma(int num) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < intArr.length; i++) {

            int value = intArr[i];

            //循环，直至num比value小
            while (num >= value) {
                num -= value;
                sb.append(StringArr[i]);
            }

            //num为0提前结束循环
            if (num == 0) {
                break;
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        System.out.println(intToRuoma(1994));

    }


}
