package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityDetailLocationsBinding
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.presentation.viewmodel.detail.DetailLocationsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.timerTask

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
            val locationDetail = detailLocationsViewModel.getDetailLocations(id)

            runOnUiThread {
                locationDetail.id?.let {
                    createUi(locationDetail)
                } ?: showErrorMessageAlert()
            }
        }
    }

    private fun showErrorMessageAlert() {
        Toast.makeText(this, "Episodio no encontrado", Toast.LENGTH_LONG).show()
        Timer().schedule(
            timerTask {
                onBackPressed()
            },
            3000,
        )
    }

    private fun createUi(location: LocationsResultsModel) {
        with(binding) {
            tvNameCreatedDetail.text = location.created
            tvNameDimensionDetail.text = location.dimension
            tvNameLocationDetail.text = location.name
            tvNameTypeDetail.text = location.type
        }
    }
}
