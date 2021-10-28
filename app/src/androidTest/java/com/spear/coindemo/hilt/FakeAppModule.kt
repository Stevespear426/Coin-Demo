package com.spear.coindemo.hilt

import com.spear.coindemo.repository.local.AppPrefs
import com.spear.coindemo.repository.local.FakeAppPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object FakeAppsModule {

    @Singleton
    @Provides
    fun provideAppPrefs(): AppPrefs {
        return FakeAppPrefs()
    }
}