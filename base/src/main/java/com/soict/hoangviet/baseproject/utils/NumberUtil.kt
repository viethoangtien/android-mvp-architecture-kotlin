package com.soict.hoangviet.baseproject.utils

import java.lang.NumberFormatException
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object NumberUtil {
    val formatter = DecimalFormat("#,###,###,###", DecimalFormatSymbols(Locale.US))

    fun formatValue(value: String): String {
        try {
            return formatter.format(removeSpecialCharacters(value).toInt())
        } catch (e: NumberFormatException) {
            return ""
        }
    }

    private fun removeSpecialCharacters(value: String): Double {
        val resultString = StringBuilder("")
        for (i in value) {
            if (i.isDigit()) {
                resultString.append(i)
            }
        }
        return resultString.toString().toDouble()
    }
}