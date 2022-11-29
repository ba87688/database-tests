package com.example.databasetesting.dependencyinjection

import android.app.Application
import com.example.databasetesting.network.database.CookieDao
import com.example.databasetesting.network.database.CookieDatabase
import com.example.databasetesting.network.remote.PixabayAPI
import com.example.databasetesting.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //application database
    @Singleton
    @Provides
    fun provideCookieDatabase(
        context:Application
    ): CookieDatabase = CookieDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCookieDao(
        db:CookieDatabase
    ):CookieDao = db.currentCookieDao()


    //retrofit
    @Singleton
    @Provides
    fun providePixabayApi():PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }


}