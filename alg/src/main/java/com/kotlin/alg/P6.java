package com.kotlin.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/09
 *     desc   : Z
 *     version: 1.0
 * </pre>
 */

class P6 {


    //Z convert
    public static String convert(String s, int numRows) {
        //
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {

            list.add(new StringBuilder());

        }

        int index = 0;

        int flag = -1;

        for (char c : s.toCharArray()) {

            //append char
            list.get(index).append(c);

            //
            if (index == 0 || index == numRows - 1) {//2
                flag = -flag;
            }

            //update index
            index += flag;

        }


        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            stringBuilder.append(list.get(i));
        }

        return stringBuilder.toString();
    }

    public static String convert2(String s, int numRows) {
        //
        String[] arr = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            arr[i] = "";
        }

        int index = 0;

        int flag = -1;

        for (char c : s.toCharArray()) {

            //append char
            arr[index] = arr[index] + c;

            //
            if (index == 0 || index == numRows - 1) {//2
                flag = -flag;
            }

            //update index
            index += flag;

        }


        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            stringBuilder.append(arr[i]);
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {


        System.out.println(convert("sdfadfadf3546545645", 3));

    }


}
