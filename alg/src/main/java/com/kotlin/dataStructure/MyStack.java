package com.kotlin.dataStructure;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/12
 *     desc   : 栈的结构
 *     version: 1.0
 * </pre>
 */

import java.util.Stack;

/**
 * 特点
 * 先进后出
 * java中通过的api有哪些
 */

//数组实现栈
/*
主要API
push() 入栈
pop() 出栈
peek() 查看栈顶元素
 */

public class MyStack<T> {

    private Object[] objArr;
    private int arrIndex = 0;

    //栈顶位置
    private int top = -1;

    private int size = 10;

    public MyStack() {
        objArr = new Object[size];
    }


    //出栈
    public T pop() {
        if (objArr.length == 0) {
            return null;
        }

//        int index = arrIndex - 1;
//        final T t = (T) objArr[index];
//        objArr[index] = null;
//        arrIndex = index;
       T t =  (T) objArr[top];
        objArr[top] = null;
        top--;
        return t;
    }

    //    public static native void arraycopy(Object src,  int  srcPos,
    //                                        Object dest, int destPos,
    //                                        int length);

    //入栈
    public void push(T t) {

        if (isFull()) {

        } else {
            top++;
            objArr[top] = t;
        }


        //        objArr[arrIndex] = t;
        //        arrIndex++;
    }

    private boolean isFull() {
        return size - 1 <= top;
    }

    //查看栈顶元素
    public T peek() {
        return (T) objArr[top];
    }


}


class StackTest {

    public static void main(String[] args) {

         Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        System.out.println(stack.toString());

        System.out.println(stack.pop());
        System.out.println(stack.peek());

        System.out.println(stack.toString());



        MyStack<String> myStack = new MyStack<>();

        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        myStack.push("4");

        System.out.println(myStack.toString());

        System.out.println(myStack.pop());
        System.out.println(myStack.peek());

        System.out.println(myStack.toString());

    }

}