package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.databinding.ActivityEpisodesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesActivity : AppCompatActivity() {

    companion object {
        const val EPISODES_API = "episode"
        const val LOG_TAG = "RickAndMortyApp"
    }

    private lateinit var binding: ActivityEpisodesBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: EpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUi()
        tapOnEpisodes()
    }

    private fun initUi() {
        binding.rvEpisodes.setHasFixedSize(true)
        adapter = EpisodesAdapter()
        binding.rvEpisodes.adapter = adapter
        binding.rvEpisodes.layoutManager = LinearLayoutManager(this)
    }

    private fun tapOnEpisodes() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val responseModelEpisodes: Response<EpisodeResponseModel> =
                retrofit.create(ApiService::class.java).getEpisodes(EPISODES_API)
            val response: EpisodeResponseModel? = responseModelEpisodes.body()
            if (responseModelEpisodes.isSuccessful) {
                Log.i(LOG_TAG, response?.info.toString())
                if (response?.results != null) {
                    runOnUiThread {
                        adapter.updateListEpisodes(response.results)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_episodes),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            } else {
                runOnUiThread {
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
}
