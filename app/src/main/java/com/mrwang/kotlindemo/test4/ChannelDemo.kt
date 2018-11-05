package com.mrwang.kotlindemo.test4

import android.view.View
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * @date 2018/8/1
 * @author chengwangyong
 */
data class Ball(var hits: Int)

object ChannelDemo {
    // 申明一个球的类
    fun channelTest() {
        launch(UI) {
            val table = Channel<Ball>() // 创建一个channel作为桌子

            launch(CommonPool) {
                player("ping", table)
            } // 选手一，先接球中


            launch(CommonPool) {
                player("pong", table)
            } // 选手二，也开始接球


            table.send(Ball(0)) // 开球，发出第一个球
            delay(3000) //
            val receive = table.receive() // 接球，终止在player中的循环发球
            println("end receive=$receive")
        }
    }

    suspend fun player(name: String, table: Channel<Ball>) {
        println("start thread=${Thread.currentThread().name}")
        for (ball in table) { // 不断接球
            ball.hits++
            println("$name $ball thread=${Thread.currentThread().name}")
            delay(300) // 等待300ms
            table.send(ball) // 发球
        }
    }

    fun View.onClick(action: suspend (View) -> Unit) {
        val eventActor = actor<View>(UI) {
            for (event in channel) action(event)
        }
        setOnClickListener {
            launch(UI){
                delay(1000L)
                eventActor.offer(it)
            }
        }


    }




    fun channelTest2() {
        launch(UI){
            val channel=Channel<Int>(0)
            launch(CommonPool){
                (1 .. 5).forEach {
                    println("channel send start it=${it*it}")
                    channel.send(it*it)
                }
                println("channel send end")
            }

            launch(CommonPool){
                (1 .. 5).forEach {
                    println("receive start")
                    println("channel receive=${channel.receive()}")
                }
            }
        }
    }


}