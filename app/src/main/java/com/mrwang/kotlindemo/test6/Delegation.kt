package com.mrwang.kotlindemo.test6

/**
 * 代理模式
 * @date 2018/9/3
 * @author chengwangyong
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print()  // prints 10
}
