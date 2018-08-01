package com.mrwang.kotlindemo.release.test4

import kotlinx.coroutines.experimental.*

/**
 * @date 2018/5/11
 * @author chengwangyong
 */
class Coroutine {

    // 最大
    // 协程 index=1183544 thread=ForkJoinPool.commonPool-worker-6
    // I/art: Clamp target GC heap from 393MB to 384MB
    fun coroutineThread() {
        val job = Job()
        val demo = job + CommonPool
        async(demo) {
            println("xxxxx")
        }
        job.cancel()

        var index = 0
        while (true) {
            launch(CommonPool) {
                index += 1
                println("协程 index=$index thread=${Thread.currentThread().name}")
                delay(Int.MAX_VALUE.toLong())
            }
        }


    }

    // 最大 I/System.out: 线程 index=13088 thread=Thread-23315
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