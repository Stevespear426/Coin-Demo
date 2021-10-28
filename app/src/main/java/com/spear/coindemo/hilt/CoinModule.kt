package com.spear.coindemo.hilt

import android.app.Application
import androidx.room.Room
import com.spear.coindemo.common.Constants.BASE_URL
import com.spear.coindemo.common.Constants.DB_NAME
import com.spear.coindemo.repository.local.CoinsDao
import com.spear.coindemo.repository.local.CoinsDb
import com.spear.coindemo.repository.remote.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {

    @Singleton
    @Provides
    fun provideCoinsDb(application: Application): CoinsDao {
        return Room.databaseBuilder(
            application,
            CoinsDb::class.java,
            DB_NAME
        ).build().coinsDao()
    }

    @Singleton
    @Provides
    fun provideCoinService(): CoinService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(CoinService::class.java)
    }
}