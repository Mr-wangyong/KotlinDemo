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
        while (true) {
           launch(CommonPool) {
                delay(Int.MAX_VALUE.toLong())
            }
        }
    }
}