package com.soict.hoangviet.baseproject.application

import android.app.Application
import android.content.Context
import com.soict.hoangviet.baseproject.BuildConfig
import com.soict.hoangviet.baseproject.util.LogUtil

class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtil.init(BuildConfig.DEBUG)
    }

    fun getContext(): Context {
        return this
    }
}