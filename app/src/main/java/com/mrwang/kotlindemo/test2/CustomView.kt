package com.mrwang.kotlindemo.release.test2

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author chengwangyong
 * @date 2018/5/30
 */
class CustomView : FrameLayout {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {

    }

}
