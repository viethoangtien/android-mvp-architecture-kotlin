package com.soict.hoangviet.baseproject.extension

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.soict.hoangviet.baseproject.delegation.FragmentBundleDataDelegate
import java.io.Serializable
import java.util.ArrayList
import kotlin.properties.ReadWriteProperty

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

fun <T> Bundle.put(key: String, value: ArrayList<T>) {
    when {
        value.size > 0 -> {
            when (value[0]) {
                is String -> putStringArrayList(key, value as ArrayList<String>)
                is Parcelable -> putParcelableArrayList(key, value as ArrayList<out Parcelable>)
            }
        }
        else -> null
    }
}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> = FragmentBundleDataDelegate()

