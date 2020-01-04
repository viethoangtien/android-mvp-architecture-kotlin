package com.soict.hoangviet.baseproject.di.builder

import com.soict.hoangviet.baseproject.ui.module.MainActivityModule
import com.soict.hoangviet.baseproject.ui.view.impl.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}