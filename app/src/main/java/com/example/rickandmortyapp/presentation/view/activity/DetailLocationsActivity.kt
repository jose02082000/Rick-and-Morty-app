package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityDetailLocationsBinding
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.presentation.viewmodel.detail.DetailLocationsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailLocationsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailLocationsBinding
    private val detailLocationsViewModel by lazy { DetailLocationsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getLocationInformation(id)
    }

    private fun getLocationInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val locationDetail = detailLocationsViewModel.getDetailLocations()

            runOnUiThread {
                if (locationDetail.isNotEmpty()) {
                    createUi(locationDetail)
                }
            }
        }
    }

    private fun createUi(location: LocationsResultsModel) {
        with(binding) {
            tvNameCreatedDetail.text = location.created
            tvNameDimensionDetail.text = location.dimension
            tvNameLocationDetail.text = location.name
            tvNameTypeDetail.text = location.type
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
