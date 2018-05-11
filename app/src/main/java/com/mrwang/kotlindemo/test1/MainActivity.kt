package com.mrwang.kotlindemo.test1

import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.mrwang.kotlindemo.R

/**
 * kotlin 空安全
 */
class MainActivity : AppCompatActivity() {
    // var  可变类型，如果作为成员，必须是 可空 类型（？）  可以多次赋值，可以赋空值 每次使用都必须检查空
    private var img: Image? = null

    // val  不可变类型 类似于 final 只能赋值一次 kotlin强烈推荐优先使用这个值
    private val img2: ImageView by lazy {
        findViewById<ImageView>(R.id.tv)
    }

    // 如果类型明确，参数后面的类型可以省略
    private val img3 by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ImageView>(R.id.tv)
    }

    // 延迟初始化的 强烈不推荐，破坏 kotlin 空检查
    private lateinit var img4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sum = fun(a: String, b: Int): String {
            return "$a+$b"
        }
        method(sum)

        sum.let {

        }
        val result = filter(mutableListOf('a', 'b', 'c', 'c', 'd', 'e')).calcu()
        println("sum=$result")

    }


    private fun method(sum: (String, Int) -> String) {
        val result = sum("a", 0)
        println("result=$result")
    }

    // 1 传递一系列值
    // 2 做过滤操作
    // 3 返回最后的 int值
    private fun List<Char>.calcu(): Int {
        return this.map {
            it.toInt()
        }.sum()
    }

    private fun filter(values: List<Char>): List<Char> {
        return values.filter {
            it != 'a'
        }
    }

    fun <P1, P2, R> Function2<P1, P2, R>.partially1(p1: P1): (P2) -> R {
        return { p2: P2 -> this(p1, p2) }
    }


}