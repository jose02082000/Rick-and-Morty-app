package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.core.RetrofitHelper.getRetrofit
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.databinding.ActivityEpisodesDetailBinding
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailEpisodesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodesDetailBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getEpisodesInformation(id)
    }

    private fun getEpisodesInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val episodeDetail = getRetrofit().create(ApiService::class.java).getEpisodesDetail(id)
            if (episodeDetail.body() != null) {
                runOnUiThread {
                    createUi(episodeDetail.body()!!)
                }
            }
        }
    }

    private fun createUi(episode: EpisodesResultModel) {
        with(binding) {
            tvNameLocationDetail.text = episode.name
            tvAirDateLocationDetail.text = episode.airDate
            tvCreatedLocationDetail.text = episode.created
            tvEpSodeLocationDetail.text = episode.episode
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
