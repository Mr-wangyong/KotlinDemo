package com.mrwang.kotlindemo.test4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

/**
 * @date 2018/5/11
 * @author chengwangyong
 */
class CoroutineActivity : AppCompatActivity() {

    private val coroutine by lazy {
        Coroutine()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("启动线程") {
                coroutine.javaThread()
            }.lparams(wrapContent, wrapContent)

            button("启动协程") {
                coroutine.coroutineThread()
            }.lparams(wrapContent, wrapContent)
        }
    }
}