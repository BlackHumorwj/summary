package com.kotlin.for2021.kotlin

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/12/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class KotlinDemo {


    init {


        fun1({ name, value ->

            1
        },{

        })

    }


}

/**
 * 高阶函数
 * @param f Function0<Int>
 */
fun fun1(f: (name:String,value:String) -> Int,f2 :(n2:String)->Unit) {
    //接收这个方法
    val value = f.invoke("李四","123")
    println("${value + 1}")

}

class Var{

    // 懒加载
    val name : String by lazy {
        System.out.println("hha")
        "123"
    }


}

