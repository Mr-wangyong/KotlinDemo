package com.mrwang.kotlindemo.release.test4

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mrwang.kotlindemo.R
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
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
        //createUI()
        //createUI2()

        createUI3()
    }

    private fun createUI() {
        verticalLayout {
            button("启动线程") {
                onClick {
                    coroutine.javaThread()
                }
            }.lparams(wrapContent, wrapContent)

            button("启动协程") {
                onClick {
                    coroutine.coroutineThread()
                }
            }.lparams(wrapContent, wrapContent)
        }
    }

    private fun createUI2() {
        verticalLayout {
            button("启动线程") {
                onClick {
                    loadImageFromCoroutine()
                }
            }.lparams(wrapContent, wrapContent)

            button("启动协程") {
                onClick {

                }
            }.lparams(wrapContent, wrapContent)
        }
    }

    private fun loadImageFromCoroutine() = launch(UI, CoroutineStart.LAZY) {
        loadImage(R.mipmap.ic_launcher)
    }

    private fun loadImage(id: Int) = async(CommonPool) {
        delay(1000L)
        BitmapFactory.decodeResource(resources, id)
    }


    private fun createUI3() {
        verticalLayout {
            button("启动协程") {
                onClick {
                    val job = runCoroutine()
                    job.start()
                }
            }.lparams(wrapContent, wrapContent)

            button("停止协程") {
                onClick {
                    job.cancel()
                }
            }.lparams(wrapContent, wrapContent)
        }
    }

    val job by lazy {
        Job()
    }

    private fun runCoroutine() = launch(UI + job, CoroutineStart.LAZY) {
       while (isActive){
           delay(1000L)
           println("coroutine is running")
       }
    }

}