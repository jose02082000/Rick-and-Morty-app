package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        initListeners()
    }

    private fun initListeners() {
        binding.imageButtonCharacters.setOnClickListener {
            tapOnCharacters()
        }
    }

    private fun tapOnCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseModel: Response<CharacterResponseModel> =
                retrofit.create(ApiService::class.java).getCharacters(CHARACTERS_API)
            val response: CharacterResponseModel? = responseModel.body()
            if (responseModel.isSuccessful) {
                Log.i(LOG_TAG, response?.info.toString())
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {
        const val CHARACTERS_API = "character"
        const val LOCATIONS_API = "location"
        const val EPISODES_API = "episode"

        const val LOG_TAG = "RickAndMortyApp"
    }
}
