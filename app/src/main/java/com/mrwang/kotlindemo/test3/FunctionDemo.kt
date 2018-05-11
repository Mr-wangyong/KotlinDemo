package com.mrwang.kotlindemo.test3

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import com.mrwang.kotlindemo.R

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

    fun main(context: Context) {
        val addOne = sum.addOne(0)
        val result = addOne(2)

        // Context 本来没有 getString 的方法的 是扩展的
        context.getString(R.string.app_name)

        val preson = Preson()
        preson.sex = "男"
        println("Preson.sex=${preson.sex}")
    }


    private val sum = fun(a: Int, b: Int): Int {
        return a + b
    }


    private fun cal(cal: (Int, Int) -> Int): () -> Unit {
        return {

        }
    }

    private fun Function2<Int, Int, Int>.addOne(a: Int): (Int) -> Int {
        return { b ->
            this(a, b)
        }
    }


    /**
     * 扩展方法 类名.方法名  可以给一个类扩展一个本来不存在的方法
     * 可以写到类里面 也可以写在类外面（不建议）
     */
    fun Context.getResString(@StringRes id: Int): String {
        return this.getString(id)
    }

    /**
     * 属性扩展 扩展一个本来没有的属性
     * 限制：属性不能被初始化
     */
    var Preson.sex: String?
        get() =  null
        set(value) {
        }

    val <T> List<T>.lastIndex: Int
        get() = size - 1

    var View. paddingLeft: Int
        set(value) {
            setPadding(value, paddingTop, paddingRight, paddingBottom)
        }
        get() {
            return paddingLeft
        }
}

