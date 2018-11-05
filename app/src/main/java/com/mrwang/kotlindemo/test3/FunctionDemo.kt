package com.mrwang.kotlindemo.release.test3

/**
 * kotlin 函数
 *
 * Kotlin 中 函数是一等公民
1.函数可以作为一个参数任意传递
2.函数可以作为另外一个函数的参数和返回值
3.具备对象的所有功能,函数也是一个对象
 * @date 2018/5/10
 * @author chengwangyong
 */
class FunctionDemo {
    var name:String?="XXX"

    fun method2(){

        name.apply2{
            println("apply")
        }


    }

    fun<T> T.apply2(block: T.() -> Unit): T { block(); return this }

}




