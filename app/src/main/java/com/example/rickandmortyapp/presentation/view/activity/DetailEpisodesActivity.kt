package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityEpisodesDetailBinding
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.presentation.viewmodel.detail.DetailEpisodeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailEpisodesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodesDetailBinding
    private val detailEpisodesViewModel by lazy { DetailEpisodeViewModel() }

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
            val episodeDetail = detailEpisodesViewModel.getDetailEpisodes(id)

            runOnUiThread {
                createUi(episodeDetail)
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
}
