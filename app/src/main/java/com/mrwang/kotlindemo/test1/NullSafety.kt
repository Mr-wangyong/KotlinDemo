package com.mrwang.kotlindemo.test1

/**
 * @date 2018/5/8
 * @author chengwangyong
 */
class NullSafety {

    var a: String? = "abc"
    // 安全类型转换
    val b: String? = a as? String


    fun method() {
        // 任何一个环节有空值， 直接返回 null
        val b = a?.length?.toShort()

        // 如果为空 返回 b 否则返回 a
        val c = a ?: b
    }



}


