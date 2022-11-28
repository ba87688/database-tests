package com.example.databasetesting.network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databasetesting.models.Cookie


@Database(entities = [Cookie::class], version = 1, exportSchema = false)
abstract class CookieDatabase: RoomDatabase() {
    abstract fun currentCookieDao():CookieDao

    companion object{
        @Volatile
        private var INSTANCE: CookieDatabase? = null

        fun getDatabase(context: Context):CookieDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CookieDatabase::class.java,
                    "cookie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}