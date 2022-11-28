package com.example.databasetesting.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cookies")
data class Cookie(
    @PrimaryKey
    var id:Int,
    var name:String
)
