package com.mrwang.kotlindemo.release.test6

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.view
import visioncore.kuaida.common.view.AnimatorType
import visioncore.kuaida.common.view.GradientStrokeView

class MainActivity : AppCompatActivity() {
    private var g:GradientStrokeView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            layoutParams = RelativeLayout.LayoutParams(matchParent, matchParent)
            backgroundColor = Color.WHITE

            g = GradientStrokeView(context, AnimatorType.Gradient )
            var layoutParams1 = RelativeLayout.LayoutParams(600, 300)
            g?.layoutParams = layoutParams1
            addView(g)
        }.view()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        g?.startAnim(AnimatorType.Gradient)
    }
}
