package com.soict.hoangviet.baseproject.extension

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException
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

fun String.toUri() = Uri.parse(this)

