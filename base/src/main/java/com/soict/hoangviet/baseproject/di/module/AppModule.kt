package com.soict.hoangviet.baseproject.di.module

import android.app.Application
import android.content.Context
import com.soict.hoangviet.baseproject.data.sharepreference.AppSharePreference
import com.soict.hoangviet.baseproject.data.sharepreference.SharePreference
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSharePreferences(context: Context): SharePreference =
        AppSharePreference(context)

}