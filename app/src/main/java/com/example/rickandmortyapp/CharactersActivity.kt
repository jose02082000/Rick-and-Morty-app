package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        initUi()
        tapOnCharacters()
    }

    private fun initUi() {
        binding.rvCharacters.setHasFixedSize(true)
        adapter = CharactersAdapters()
        binding.rvCharacters.adapter = adapter
        val manager = GridLayoutManager(this, 2)
        binding.rvCharacters.layoutManager = manager
    }

    private fun tapOnCharacters() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val responseModel: Response<CharacterResponseModel> =
                retrofit.create(ApiService::class.java).getCharacters(CHARACTERS_API)
            val response: CharacterResponseModel? = responseModel.body()
            if (responseModel.isSuccessful) {
                Log.i(LOG_TAG, response?.info.toString())
                if (response?.results != null) {
                    runOnUiThread {
                        adapter.updateListCharacters(response.results)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            applicationContext,
                            R.string.toast_character,
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
