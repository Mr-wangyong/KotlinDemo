package com.mrwang.kotlindemo.test4

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * @date 2018/5/11
 * @author chengwangyong
 */
class Coroutine {

    fun coroutineThread() {
        var index = 0
        while (true) {
            launch(CommonPool) {
                index += 1
                println("协程 index=$index thread=${Thread.currentThread().name}")
                delay(Int.MAX_VALUE.toLong())
            }
        }
    }

    fun javaThread() {
        var index = 0
        while (true) {
            Thread {
                run {
                    index += 1
                    println("线程 index=$index thread=${Thread.currentThread().name}")
                    Thread.sleep(Int.MAX_VALUE.toLong())
                }
            }.start()
        }
    }
}