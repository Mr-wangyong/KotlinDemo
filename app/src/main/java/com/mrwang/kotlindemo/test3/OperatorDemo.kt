package com.mrwang.kotlindemo.test3

import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * @date 2018/7/30
 * @author chengwangyong
 */
class OperatorDemo {


    fun main() {
        for (i in 1..100) {

        }
    }


    // 中缀表示法
    class Person(val name: String) {
        infix fun 说(word: String) {
            println("\"$name 说 $word\"")
        }

    }

    fun main(args: Array<String>) {
        val 老王 = Person("老王")
        老王 说 "我终于会写 kotlin 了"


        300L timeOut {

        }
    }





}

infix fun Long.timeOut(call: () -> Unit) {
    val long = this
    launch(Unconfined) {
        delay(long)
        call.invoke()
    }
}
infix fun Any.printString(str: String){
    println(str)
}


