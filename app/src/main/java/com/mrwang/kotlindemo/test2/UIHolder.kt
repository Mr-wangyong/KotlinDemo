package com.mrwang.kotlindemo.test2

import android.app.Activity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * @date 2018/5/10
 * @author chengwangyong
 */
class UIHolder : AnkoComponent<Activity> {

    override fun createView(ui: AnkoContext<Activity>): View {
//        return with(ui) {
//            verticalLayout {
//                val name = editText()
//                button("Say Hello") {
//                    onClick { ctx.toast("Hello, ${name.text}!") }
//                }
//            }
//        }
        return ui.run {
            verticalLayout {
                val name = editText()
                button("Say Hello") {
                    onClick { ctx.toast("Hello, ${name.text}!") }
                }
            }
        }

    }

}