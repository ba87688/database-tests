package com.example.databasetesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databasetesting.models.Cookie
import com.example.databasetesting.network.database.CookieDao
import com.example.databasetesting.network.remote.PixabayAPI
import com.example.databasetesting.network.remote.responses.ImageResponse
import com.example.databasetesting.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CookieRepository @Inject constructor(
    val dao:CookieDao,
    val pixabayAPI: PixabayAPI

    ) :CookieRepositoryInterface {


    override suspend fun insert(cookie: Cookie) {
            dao.insert(cookie)

    }

    override suspend fun delete(cookie: Cookie) {
            dao.delete(cookie)

    }

    override suspend fun getById(id: Int) :Cookie {
        val cookie:Cookie
        cookie= dao.getById(id)

        return cookie
    }

    override fun getAllCookiesFlow(): MutableLiveData<List<Cookie>> {
        return dao.getAllCookiesFlow()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            //get pixabay api
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("error",null)
            }else{
                Resource.error("Error occured",null)
            }
        }catch (e:Exception){
            Resource.error("could not reach servers. Check internet",null)
        }
    }

}