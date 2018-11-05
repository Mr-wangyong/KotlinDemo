package com.mrwang.kotlindemo.test4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * @date 2018/7/22
 * @author chengwangyong
 */

class CoroutineDemo {

    fun main() {

        launch(Unconfined) {
            delay(1000L)
            println("HelloWorld")
        }

        runBlocking {
            delay(1000L)
            println("HelloWorld")
        }


        val job = kotlinx.coroutines.experimental.async(CommonPool) {
            delay(1000L)
            println("HelloWorld")
        }

        launch(UI) {
            val img1 = async(CommonPool) { loadImage() }
            val img2 = async(CommonPool) { loadImage() }
            onImageGet(img1.await(), img2.await())
        }
    }

    private fun onImageGet(await: Bitmap, await1: Bitmap) {

    }

    suspend fun methodName(): Int {
        kotlinx.coroutines.experimental.run(CommonPool) {

        }
        Thread.sleep(5000L)
        return 1
    }

    suspend fun loadImage(): Bitmap {
        delay(1000L)
        return BitmapFactory.decodeFile("")
    }


}