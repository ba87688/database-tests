package com.example.databasetesting.repository

import com.example.databasetesting.models.Cookie
import com.example.databasetesting.network.database.CookieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CookieRepository @Inject constructor(
    val dao:CookieDao) :CookieRepositoryInterface {
    override suspend fun insert(cookie: Cookie) {
        withContext(Dispatchers.IO){
            dao.insert(cookie)
        }
    }

    override suspend fun delete(cookie: Cookie) {
        withContext(Dispatchers.IO){
            dao.delete(cookie)
        }
    }

    override suspend fun getById(id: Int) :Cookie {
        val cookie:Cookie
        withContext(Dispatchers.IO){
            cookie= dao.getById(id)
        }
        return cookie
    }

}