package com.mrwang.kotlindemo.release.test1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mrwang.kotlindemo.R

/**
 * kotlin 空安全
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}