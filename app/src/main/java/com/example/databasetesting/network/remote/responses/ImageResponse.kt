package com.example.databasetesting.network.remote.responses

data class ImageResponse(
    val hits:List<ImageResult>,
    val total:Int,
    val totalHits:Int

)
