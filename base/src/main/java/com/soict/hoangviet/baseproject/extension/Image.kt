package com.soict.hoangviet.baseproject.extension

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.soict.hoangviet.baseproject.module.GlideApp

/**
 * Load Image with GlideApp
 */
inline fun <reified T : Any> ImageView.loadImage(
    context: Context,
    image: T,
    @DrawableRes placeHolder: Int,
    @DrawableRes error: Int
) {
    when (T::class) {
        Int::class.java, String::class.java, Uri::class.java -> {
            GlideApp.with(context)
                .load(image)
                .placeholder(placeHolder)
                .error(error)
                .into(this)
        }
    }

}
