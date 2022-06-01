package com.kotlin.alg.letcode;

import java.util.Stack;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class StackDemo {


    //20. 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        //左括号入栈 右括号和栈顶比较
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if ((stack.isEmpty() || (c == ')' && stack.pop() != '(') || (c == ']' && stack.pop() != '[') || (c == '}' && stack.pop() != '{'))) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
