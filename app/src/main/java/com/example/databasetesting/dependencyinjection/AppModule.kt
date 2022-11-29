package com.example.databasetesting.dependencyinjection

import android.app.Application
import com.example.databasetesting.network.database.CookieDao
import com.example.databasetesting.network.database.CookieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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



}