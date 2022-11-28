package com.example.databasetesting.repository

import com.example.databasetesting.models.Cookie

interface CookieRepositoryInterface {

    suspend fun insert(cookie:Cookie)
    suspend fun delete(cookie:Cookie)
    suspend fun getById(id:Int):Cookie

}