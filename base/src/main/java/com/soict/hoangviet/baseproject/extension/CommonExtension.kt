package com.soict.hoangviet.baseproject.extension

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.soict.hoangviet.baseproject.module.GlideApp
import java.util.regex.Pattern

const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
const val EMAIL_TWO_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+\\.[a-z]+"
const val PHONE_PATTERN = "[0-9]{9,10}"
const val STRONG_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{7,}$"

/**
 * Validate input
 */
fun String.isValidateEmail(): Boolean {
    return (Pattern.compile(EMAIL_PATTERN).matcher(this).matches()
            || Pattern.compile(EMAIL_TWO_PATTERN).matcher(this).matches())
}

fun String.isValidatePhoneNumber(): Boolean {
    return Pattern.compile(PHONE_PATTERN).matcher(this).matches()
}

fun String.isValidatePassword(): Boolean {
    return Pattern.compile(STRONG_PASSWORD_PATTERN).matcher(this).matches()
}

/**
 * Inflate a new view from the specified xml resource
 */
fun Context.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this).inflate(layoutRes, null)
}

fun Context.inflate(@LayoutRes layoutRes: Int, root: ViewGroup): View {
    return LayoutInflater.from(this).inflate(layoutRes, root, false)
}

fun Context.inflate(@LayoutRes layoutRes: Int, root: ViewGroup, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(layoutRes, root, attachToRoot)
}

/**
 * View visibility
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * Load Image with GlideApp
 */
fun ImageView.loadImageDrawable(
    context: Context,
    @DrawableRes drawableRes: Int,
    @DrawableRes placeHolder: Int,
    @DrawableRes error: Int
) {
    GlideApp.with(context)
        .load(drawableRes)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadImageUrl(
    context: Context,
    string: String,
    @DrawableRes placeHolder: Int,
    @DrawableRes error: Int
) {
    GlideApp.with(context)
        .load(string)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadImageUri(
    context: Context,
    uri: Uri,
    @DrawableRes placeHolder: Int,
    @DrawableRes error: Int
){
    GlideApp.with(context)
        .load(uri)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}


