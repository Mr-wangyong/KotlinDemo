package com.mrwang.kotlindemo.test2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.verticalLayout

/**
 * @author chengwangyong
 * @date 2018/5/30
 */
class CustomView : View {
    constructor(context: Context) : this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init(){
        context.apply {
            verticalLayout {
                button("customView"){
                    onClick {
                        println("customView")
                    }
                }
            }
        }
    }

}
