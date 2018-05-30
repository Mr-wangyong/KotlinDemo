package com.mrwang.kotlindemo.test2

import android.app.Activity
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import org.jetbrains.anko.*
import kotlin.properties.Delegates

/**
 * @date 2018/5/10
 * @author chengwangyong
 */
class UIHolder : AnkoComponent<Activity> {
    private val uiData by lazy {
        UIData()
    }
    private var nameView: TextView? = null
    private var ageView: TextView? = null
    private var radio: RadioGroup? = null

    override fun createView(ui: AnkoContext<Activity>): View {
        return ui.let {
            it.run {
                verticalLayout {
                    nameView = textView {

                    }
                    ageView = textView{

                    }
                    radio = radioGroup {
                        radioButton {
                            text = "男"
                            check(uiData.sex == 0)
                        }
                        radioButton {
                            text = "女"
                            check(uiData.sex == 1)
                        }
                    }
                }
            }
        }
    }

    inner class UIData {
        var name: String by Delegates.observable("") {
            prop, old, new ->
            nameView?.text="name:$new"
        }
        var age: String by Delegates.observable(""){
            property, oldValue, newValue ->
            ageView?.text="age:$name"
        }
        var sex: Int = 0
    }
}