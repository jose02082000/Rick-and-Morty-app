package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.databinding.ActivityCharactersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersActivity : AppCompatActivity() {

    companion object {
        const val CHARACTERS_API = "character"
        const val LOG_TAG = "RickAndMortyApp"
    }

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: CharactersAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        binding.rvCharacters.setHasFixedSize(true)
        adapter = CharactersAdapters()
        binding.rvCharacters.adapter = adapter
        val manager = GridLayoutManager(this, 2)
        binding.rvCharacters.layoutManager = manager
        tapOnCharacters()
    }

    private fun tapOnCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseModel: Response<CharacterResponseModel> =
                retrofit.create(ApiService::class.java).getCharacters(CHARACTERS_API)
            val response: CharacterResponseModel? = responseModel.body()
            if (responseModel.isSuccessful) {
                Log.i(LOG_TAG, response?.info.toString())
            }
            if (response?.results != null) {
                runOnUiThread {
                    adapter.updateList(response.results)
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
