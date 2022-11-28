package com.example.databasetesting

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.databasetesting.databinding.ActivityMainBinding
import com.example.databasetesting.models.Cookie
import com.example.databasetesting.network.database.CookieDatabase
import com.example.databasetesting.repository.CookieRepository
import com.example.databasetesting.viewmodels.CookieViewModelFactory
import com.example.databasetesting.viewmodels.CookiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: CookiesViewModel
    @Inject
    lateinit var db:CookieDatabase

    @Inject
    lateinit var repo:CookieRepository




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val viewModelFactory = CookieViewModelFactory(db,application,repo,null,this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CookiesViewModel::class.java)
        binding.databaseAdd.setOnClickListener {

            val cookie= Cookie(1,"Evan")

            viewModel.insert(cookie)

            Log.d("MAIN ACTIVITY", "onCreate: ${cookie.name}")

        }



    }
}