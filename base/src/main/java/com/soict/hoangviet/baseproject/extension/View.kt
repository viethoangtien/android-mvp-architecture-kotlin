package com.soict.hoangviet.baseproject.extension

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

const val CLICK_THROTTLE_DELAY = 600L

fun View.onAvoidDoubleClick(
    throttleDelay: Long = CLICK_THROTTLE_DELAY,
    onClick: (View) -> Unit
) {
    setOnClickListener {
        onClick(this)
        isClickable = false
        postDelayed({ isClickable = true }, throttleDelay)
    }
}

/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */
fun View.getString(stringResId: Int): String = resources.getString(stringResId)

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyBoard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * Try to hide the keyboard and returns whether it worked
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

/**
 * Extension method to simplify view binding.
 */
fun <T : ViewDataBinding> View.bind() = DataBindingUtil.bind<T>(this) as T

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackBar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

/**
 * Extension method to update padding of view.
 *
 */
fun View.updatePadding(paddingStart: Int = getPaddingStart(),
                       paddingTop: Int = getPaddingTop(),
                       paddingEnd: Int = getPaddingEnd(),
                       paddingBottom: Int = getPaddingBottom()) {
    setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

/**
 * Extension method to set View's left padding.
 */
fun View.setPaddingLeft(value: Int) = setPadding(value, paddingTop, paddingRight, paddingBottom)

/**
 * Extension method to set View's right padding.
 */
fun View.setPaddingRight(value: Int) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

/**
 * Extension method to set View's top padding.
 */
fun View.setPaddingTop(value: Int) = setPaddingRelative(paddingStart, value, paddingEnd, paddingBottom)

/**
 * Extension method to set View's bottom padding.
 */
fun View.setPaddingBottom(value: Int) = setPaddingRelative(paddingStart, paddingTop, paddingEnd, value)

/**
 * Extension method to set View's start padding.
 */
fun View.setPaddingStart(value: Int) = setPaddingRelative(value, paddingTop, paddingEnd, paddingBottom)

/**
 * Extension method to set View's end padding.
 */
fun View.setPaddingEnd(value: Int) = setPaddingRelative(paddingStart, paddingTop, value, paddingBottom)

/**
 * Set an onclick listener
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }

/**
 * Extension method to set OnClickListener on a view.
 */
fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

/**
 * Show the view if [condition] returns true
 * (visibility = View.VISIBLE)
 */
inline fun View.showIf(condition: () -> Boolean) : View {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.INVISIBLE)
 */
fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Hide the view if [predicate] returns true
 * (visibility = View.INVISIBLE)
 */
inline fun View.hideIf(condition: () -> Boolean) : View {
    if (visibility != View.INVISIBLE && condition()) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

/**
 * Remove the view if [predicate] returns true
 * (visibility = View.GONE)
 */
inline fun View.removeIf(condition: () -> Boolean) : View {
    if (visibility != View.GONE && condition()) {
        visibility = View.GONE
    }
    return this
}

/**
 * Toggle a view's visibility
 */
fun View.toggleVisibility() : View {
    if (visibility == View.VISIBLE) {
        visibility = View.INVISIBLE
    } else {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Like findViewById but with type interference, assume the view exists
 */
inline fun <reified T : View> View.find(@IdRes id: Int) : T = findViewById(id)

/**
 * Extension method to provide quicker access to the [LayoutInflater] from a [View].
 */
fun View.getLayoutInflater() = context.getLayoutInflater()