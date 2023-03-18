package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.Utils
import com.example.rickandmortyapp.databinding.ActivityLocationsBinding
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.presentation.view.adapter.LocationAdapter
import com.example.rickandmortyapp.presentation.viewmodel.LocationsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    private val locationsViewModel by lazy { LocationsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initRecyclerView(locationList: List<LocationsResultsModel>) {
        with(binding) {
            rvLocations.setHasFixedSize(true)
            adapter = LocationAdapter(locationList) { navigateToDetailLocations(it) }
            rvLocations.adapter = adapter
            val manager = GridLayoutManager(this@LocationsActivity, 3)
            rvLocations.layoutManager = manager
            progressBar.isVisible = false
        }
    }

    private fun initialize() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val locationsList = locationsViewModel.getLocationList()

            runOnUiThread {
                if (locationsList.isNotEmpty()) {
                    initRecyclerView(locationsList)
                } else {
                    binding.progressBar.isVisible = false
                    utils.showToastOnError(
                        applicationContext,
                        getString(R.string.toast_location),
                    )
                }
            }
        }
    }

    private fun navigateToDetailLocations(id: Int) {
        val intent = Intent(this, DetailLocationsActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
