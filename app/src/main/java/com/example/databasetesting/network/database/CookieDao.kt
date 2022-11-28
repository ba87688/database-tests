package com.example.databasetesting.network.database

import androidx.room.*
import com.example.databasetesting.models.Cookie

@Dao
interface CookieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cookie:Cookie)

    @Delete
    suspend fun delete(cookie:Cookie)

    @Query("SELECT * FROM cookies WHERE :id = id")
    suspend fun getById(id:Int):Cookie


}