package com.example.kotlindemo.hilt

import android.app.Application
import com.example.kotlindemo.repository.local.AppPrefs
import com.example.kotlindemo.repository.local.AppPrefsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppPrefs(application: Application): AppPrefs {
        return AppPrefsImpl(application);
    }
}