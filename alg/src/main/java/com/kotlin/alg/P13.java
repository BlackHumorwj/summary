package com.kotlin.alg;

import java.util.HashMap;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class P13 {

    static HashMap<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);

        }
    };

    private static Integer Roman2Int(String str) {

        int value = 0;
        final int length = str.length();
        for (int i = 0; i < length; i++) {

            final char c = str.charAt(i);

            //i<length-1
            if ( i<length-1 && map.get(c) < map.get(str.charAt(i+1))){
                //
                value -= map.get(c);
            }else {
                value += map.get(c);
            }

        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(Roman2Int("MCMXCIV"));
    }
}
