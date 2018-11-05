package com.mrwang.kotlindemo.test2

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.mrwang.kotlindemo.R
import org.jetbrains.anko.*

/**
 * @date 2018/9/2
 * @author chengwangyong
 */
class NormalCustomView : RelativeLayout {
    private var title: TextView? = null
    private var subTitle: TextView? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        relativeLayout {
            imageView {
                id = R.id.iv
                imageResource = R.drawable.momo_app_icon

            }.lparams(dip(40), dip(40)) {
                alignParentLeft()
                centerVertically()
            }


            textView {
                id = R.id.text
            }.lparams(wrapContent, wrapContent) {
                rightOf(R.id.iv)
                leftMargin = dip(5)
            }.apply {
                this@NormalCustomView.title = this
            }
            textView {
                id = R.id.text2
            }.lparams(wrapContent, wrapContent) {
                rightOf(R.id.iv)
                below(R.id.text)
                leftMargin = dip(5)
                topMargin = dip(2)
            }.apply {
                this@NormalCustomView.subTitle = this
            }

        }
    }

    public fun setData(title: String?, subTitle: String?) {
        this.title?.text = title
        this.subTitle?.text = subTitle
    }
}