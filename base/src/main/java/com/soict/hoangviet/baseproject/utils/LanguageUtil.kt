package com.soict.hoangviet.baseproject.utils

import android.app.Activity
import android.content.Context
import java.util.*

object LanguageUtil {
    fun setCurrentLanguage(activity: Activity, codeLocale: String) {
        val locale = Locale(codeLocale)
        Locale.setDefault(locale)
        val configuration = activity.baseContext.applicationContext.resources.configuration
        configuration.setLocale(locale)
        activity.baseContext.resources?.let { resource ->
            resource.updateConfiguration(configuration, resource.displayMetrics)
        }
        activity.baseContext.applicationContext.resources?.let { resource ->
            resource.updateConfiguration(configuration, resource.displayMetrics)
        }
    }
}