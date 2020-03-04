package com.soict.hoangviet.baseproject.extension

import android.graphics.Paint
import android.widget.TextView

/**
 * Extension method underLine for TextView.
 */
fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * Extension method deleteLine for TextView.
 */
fun TextView.strikeThrough() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * Extension method bold for TextView.
 */
fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}

/**
 * Extension method to set a drawable to the left of a TextView.
 */
fun TextView.setDrawableLeft(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0)
}

/**
 * Extension method to set a drawable to the left of a TextView.
 */
fun TextView.setDrawableRight(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
}