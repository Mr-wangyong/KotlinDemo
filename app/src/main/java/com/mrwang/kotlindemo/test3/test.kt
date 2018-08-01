package com.mrwang.kotlindemo.release.test3

/**
 * @date 2018/7/19
 * @author chengwangyong
 */
internal val a = 0

class test {
    var a = "String"
    fun main() {
        //
        a.let {
            println(method(a))
        }
        a.let2 {
            println(it)
        }
    }

    fun method(a: String): String {
        return a
    }

}

public fun <T, R> T.let2(block: (T) -> R): R {
    return block(this)
}
