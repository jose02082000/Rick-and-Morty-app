package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.Utils
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.databinding.ActivityEpisodesBinding
import com.example.rickandmortyapp.domain.model.EpisodeResponseModel
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.presentation.view.adapter.EpisodesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "RickAndMortyApp"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityEpisodesBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: EpisodesAdapter
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        tapOnEpisodes()
    }

    private fun initRecyclerView(episodeList: List<EpisodesResultModel>) {
        binding.rvEpisodes.setHasFixedSize(true)
        adapter = EpisodesAdapter(episodeList) { navigateToEpisodesDetails(it) }
        val layoutManager = LinearLayoutManager(this)
        binding.rvEpisodes.adapter = adapter
        binding.rvEpisodes.layoutManager = layoutManager

        binding.progressBar.isVisible = false
    }

    private fun tapOnEpisodes() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val responseModelEpisodes: Response<EpisodeResponseModel> =
                retrofit.create(ApiService::class.java).getEpisodes()
            val response: EpisodeResponseModel? = responseModelEpisodes.body()

            runOnUiThread {
                if (responseModelEpisodes.isSuccessful) {
                    Log.i(LOG_TAG, response?.info.toString())
                    if (response?.results != null) {
                        initRecyclerView(response.results)
                    } else {
                        binding.progressBar.isVisible = false
                        utils.showToastOnError(
                            applicationContext,
                            getString(R.string.toast_episodes),
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

    private fun navigateToEpisodesDetails(id: String) {
        val intent = Intent(this, DetailEpisodesActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
