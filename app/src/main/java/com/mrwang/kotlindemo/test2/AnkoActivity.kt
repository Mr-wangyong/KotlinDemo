package com.mrwang.kotlindemo.release.test2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewManager
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * AnkoLayout 的使用
 * https://github.com/Kotlin/anko
 * @author chengwangyong
 * @date 2018/5/9
 */
class AnkoActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("Hello") {

            }
            button("world") {

            }.lparams(wrapContent, wrapContent)

            customView {

            }

            textView {

            }.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            })
        }


    }

    fun ViewManager.customView() = customView() {}

    fun ViewManager.customView(init: CustomView.() -> Unit): CustomView {
        return ankoView({ CustomView(it) }, theme = 0, init = init)
    }
}



