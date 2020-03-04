package com.soict.hoangviet.baseproject.extension

import android.graphics.Bitmap
import android.os.Handler
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Extension method to get the TAG name for all object
 */
fun <T : Any> T.TAG() = this::class.simpleName

/**
 * Extension method to get base64 string for Bitmap.
 */
fun Bitmap.toBase64(): String {
    var result = ""
    val baos = ByteArrayOutputStream()
    try {
        compress(Bitmap.CompressFormat.JPEG, 100, baos)
        baos.flush()
        baos.close()
        val bitmapBytes = baos.toByteArray()
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            baos.flush()
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}

/**
 * Extension method to run block of code after specific Delay.
 */
fun runDelayed(action: () -> Unit, delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS) {
    Handler().postDelayed(action, timeUnit.toMillis(delay))
}


