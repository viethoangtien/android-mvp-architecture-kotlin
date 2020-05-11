package com.soict.hoangviet.baseproject.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

object LanguageUtil {
    fun setCurrentLanguage(context: Context, codeLocale: String): Context {
        val locale = Locale(codeLocale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            configuration.locales = localeList
        } else {
            configuration.setLocale(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}