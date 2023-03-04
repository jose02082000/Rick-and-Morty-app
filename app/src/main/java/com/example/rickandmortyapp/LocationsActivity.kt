package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.databinding.ActivityLocationsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationsActivity : AppCompatActivity() {

    companion object {
        const val LOCATIONS_API = "location"
        const val LOG_TAG = "RickAndMortyApp"
    }

    private lateinit var binding: ActivityLocationsBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUi()
        tapOnLocations()
    }

    private fun initUi() {
        binding.rvLocations.setHasFixedSize(true)
        adapter = LocationAdapter()
        binding.rvLocations.adapter = adapter
        val manager = GridLayoutManager(this, 3)
        binding.rvLocations.layoutManager = manager
    }

    private fun tapOnLocations() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseModelLocations: Response<LocationsResponseModel> =
                retrofit.create(ApiService::class.java).getLocations(LOCATIONS_API)
            val response: LocationsResponseModel? = responseModelLocations.body()
            if (responseModelLocations.isSuccessful) {
                Log.i(LOG_TAG, response?.results.toString())
                if (response?.results != null) {
                    runOnUiThread {
                        adapter.updateList(response.results)
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
