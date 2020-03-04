package com.soict.hoangviet.baseproject.di.component

import android.app.Application
import android.content.Context
import com.soict.hoangviet.baseproject.application.BaseApplication
import com.soict.hoangviet.baseproject.di.builder.ActivityBuilder
import com.soict.hoangviet.baseproject.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        (AndroidInjectionModule::class),
        (AppModule::class),
        (ActivityBuilder::class)]
)
@Singleton
interface AppComponent {

    fun getContext(): Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)
}