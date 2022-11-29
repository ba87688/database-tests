package com.example.databasetesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databasetesting.models.Cookie
import com.example.databasetesting.network.remote.responses.ImageResponse
import com.example.databasetesting.util.Resource
import retrofit2.Response

interface CookieRepositoryInterface {

    //database
    suspend fun insert(cookie:Cookie)
    suspend fun delete(cookie:Cookie)
    suspend fun getById(id:Int):Cookie
    fun getAllCookiesFlow(): MutableLiveData<List<Cookie>>

    //
    suspend fun searchForImage(imageQuery:String): Resource<ImageResponse>

}