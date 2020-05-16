package com.soict.hoangviet.baseproject.utils

import android.app.Activity
import android.content.Context
import java.util.*

object LanguageUtil {
    fun setCurrentLanguage(activity: Activity, codeLocale: String): Context {
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
        return activity
    }

    fun setCurrentLanguage(context: Context, codeLocale: String): Context {
        val locale = Locale(codeLocale)
        Locale.setDefault(locale)
        val resource = context.resources
        val configuration = resource.configuration
        configuration.setLocale(locale)
        resource.updateConfiguration(configuration, resource.displayMetrics)
        return context
    }
}