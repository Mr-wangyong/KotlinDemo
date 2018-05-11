package com.mrwang.kotlindemo.test2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * AnkoLayout 的使用
 * https://github.com/Kotlin/anko
 * @author chengwangyong
 * @date 2018/5/9
 */
class AnkoActivity : AppCompatActivity() {
    private val uiHolder by lazy {
        UIHolder()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiHolder.createUI()
    }
}
