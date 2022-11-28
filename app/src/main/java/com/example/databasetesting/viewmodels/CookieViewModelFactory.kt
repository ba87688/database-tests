package com.example.databasetesting.viewmodels

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.databasetesting.network.database.CookieDatabase
import com.example.databasetesting.repository.CookieRepository
import javax.inject.Inject

class CookieViewModelFactory @Inject constructor(
    private val db:CookieDatabase,
    private val app: Application,
    private val repository: CookieRepository,
    defaultArgs: Bundle? = null,
    savedStateRegistryOwner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner,defaultArgs) {







    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(CookiesViewModel::class.java)) {
            return CookiesViewModel(app,db, repository,handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")    }

}