package com.example.hanxiao.kttest

import android.content.res.Resources
import android.util.TypedValue

fun<T: Int> T.uiPx(): Int = dpToPx(this as Int)

private fun dpToPx(dp: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics).toInt()


fun <T : Number> T.uiPX(): Int = pxFromDp(
        dpFromUIPX(this as Int).toInt()
)

fun <T : Number> T.uiFloat(): Float = this.uiPX().toFloat()

fun dpFromUIPX(px: Int): Float = px * 1.018f
fun pxFromDp(dp: Int): Int = (dp * Resources.getSystem().displayMetrics.density).toInt()

fun Float.half(): Float = this / 2f
fun Int.half(): Int = this / 2
fun Double.half(): Double = this / 2.0
fun Int.halfToFloat(): Float = this / 2f

fun Int?.isZero(): Boolean = this == 0

