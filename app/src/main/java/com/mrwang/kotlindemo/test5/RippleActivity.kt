package com.mrwang.kotlindemo.test5

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.button
import org.jetbrains.anko.verticalLayout

/**
 * @author chengwangyong
 * @date 2018/5/16
 */
class RippleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            val rippleView = RadioRippleView(applicationContext).lparams(200, 200)
            addView(rippleView)
            clipChildren = false
            clipToPadding = false
            rippleView.startLooperAnim()
            backgroundColor= Color.BLACK


            button {

            }
        }
    }
}
