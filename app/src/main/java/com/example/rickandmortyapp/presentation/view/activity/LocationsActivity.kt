package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.domain.model.LocationsResponseModel
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.Utils
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.databinding.ActivityLocationsBinding
import com.example.rickandmortyapp.presentation.view.adapter.LocationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationsActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "RickAndMortyApp"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityLocationsBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: LocationAdapter
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        tapOnLocations()
    }

    private fun initRecyclerView(locationList: List<LocationsResultsModel>) {
        binding.rvLocations.setHasFixedSize(true)
        adapter = LocationAdapter(locationList) { navigateToDetailLocations(it) }
        binding.rvLocations.adapter = adapter
        val manager = GridLayoutManager(this, 3)
        binding.rvLocations.layoutManager = manager

        binding.progressBar.isVisible = false
    }

    private fun tapOnLocations() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val responseModelLocations: Response<LocationsResponseModel> =
                retrofit.create(ApiService::class.java).getLocations()
            val response: LocationsResponseModel? = responseModelLocations.body()

            runOnUiThread {
                if (responseModelLocations.isSuccessful) {
                    Log.i(LOG_TAG, response?.results.toString())
                    if (response?.results != null) {
                        initRecyclerView(response.results)
                    } else {
                        binding.progressBar.isVisible = false
                        utils.showToastOnError(
                            applicationContext,
                            getString(R.string.toast_location),
                        )
                    }
                } else {
                    binding.progressBar.isVisible = false
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

    private fun navigateToDetailLocations(id: Int) {
        val intent = Intent(this, DetailLocationsActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
