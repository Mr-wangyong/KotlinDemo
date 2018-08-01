package com.mrwang.kotlindemo.test3

import android.content.Context
import android.widget.Toast

/**
 * @date 2018/7/22
 * @author chengwangyong
 */
class ExpendDemo {

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

}

