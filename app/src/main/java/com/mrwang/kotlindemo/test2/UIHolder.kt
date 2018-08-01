package com.mrwang.kotlindemo.release.test2

import android.app.Activity
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * @date 2018/5/10
 * @author chengwangyong
 */
class UIHolder : AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>): View {
        return ui.run {
            verticalLayout {
                textView(){

                }
            }
        }
    }
}