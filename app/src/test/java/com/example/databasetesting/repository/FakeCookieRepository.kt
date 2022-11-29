package com.example.databasetesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databasetesting.network.remote.responses.ImageResponse
import com.example.databasetesting.util.Resource
import com.example.databasetesting.models.Cookie

class FakeCookieRepository :CookieRepositoryInterface  {

    private val cookieItems = mutableListOf<Cookie>()


    private val observableCookies = MutableLiveData<List<Cookie>>(cookieItems)
    private val observableTotalCookie = MutableLiveData<Cookie> ()


    private var shouldRetrunNetworkError = false

    fun setShouldReturnNetworkError(value:Boolean){
        shouldRetrunNetworkError = value
    }

    override suspend fun insert(cookie: Cookie) {
        cookieItems.add(cookie)
        refreshCookies()
    }

    override suspend fun delete(cookie: Cookie) {
        cookieItems.remove(cookie)
        refreshCookies()
    }

    override suspend fun getById(id: Int): Cookie {
        var cook = Cookie(-1,"")

        for (i in cookieItems){
            if(i.id ==id){
                cook= i
            }
        }
        return cook
    }

    private fun refreshCookies(){
        observableCookies.postValue(cookieItems)
    }
    override fun getAllCookiesFlow(): MutableLiveData<List<Cookie>> {

        return observableCookies
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldRetrunNetworkError){
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(),0,0))
        }

    }


}