package com.soict.hoangviet.baseproject.application

import android.app.Activity
import android.app.Application
import android.content.Context
import com.soict.hoangviet.baseproject.BuildConfig
import com.soict.hoangviet.baseproject.di.component.DaggerAppComponent
import com.soict.hoangviet.baseproject.utils.LogUtil
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtil.init(BuildConfig.DEBUG)
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    fun getContext(): Context {
        return instance
    }
}