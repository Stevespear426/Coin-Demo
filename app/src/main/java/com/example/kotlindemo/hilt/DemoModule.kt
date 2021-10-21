package com.example.kotlindemo.hilt

import android.app.Application
import androidx.room.Room
import com.example.kotlindemo.common.Constants.BASE_URL
import com.example.kotlindemo.common.Constants.DB_NAME
import com.example.kotlindemo.repository.local.CoinsDao
import com.example.kotlindemo.repository.local.CoinsDb
import com.example.kotlindemo.repository.remote.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DemoModule {

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