package com.example.databasetesting.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.databasetesting.models.Cookie
import com.example.databasetesting.network.database.CookieDatabase
import com.example.databasetesting.repository.CookieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class CookiesViewModel @AssistedInject constructor(
    application: Application,
    val db: CookieDatabase,
    val repository: CookieRepository,
    @Assisted val state: SavedStateHandle,

    ): AndroidViewModel(application) {


    fun insert(cookie: Cookie) {
        viewModelScope.launch {
            repository.insert(cookie)
        }
    }


}