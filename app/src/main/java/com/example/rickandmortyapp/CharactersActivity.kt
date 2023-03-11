package com.example.rickandmortyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: CharactersAdapters
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        tapOnCharacters()
    }

    private fun initRecyclerView(charactersList: List<CharactersResultModel>) {
        binding.rvCharacters.setHasFixedSize(true)
        adapter = CharactersAdapters(charactersList) { navigateToDetailCharacter(it) }
        binding.rvCharacters.adapter = adapter
        val manager = GridLayoutManager(this, 2)
        binding.rvCharacters.layoutManager = manager

        binding.progressBar.isVisible = false
    }

    private fun tapOnCharacters() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val responseModel: Response<CharacterResponseModel> =
                retrofit.create(ApiService::class.java).getCharacters(CHARACTERS_API)
            val response: CharacterResponseModel? = responseModel.body()

            runOnUiThread {
                if (responseModel.isSuccessful) {
                    Log.i(LOG_TAG, response?.info.toString())
                    if (response?.results != null) {
                        initRecyclerView(response.results)
                    } else {
                        binding.progressBar.isVisible = false
                        utils.showToastOnError(
                            applicationContext,
                            getString(R.string.toast_character),
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

    private fun navigateToDetailCharacter(name: String) {
        val intent = Intent(this, DetailCharactersActivity::class.java)
        intent.putExtra(EXTRA_ID, name)
        startActivity(intent)
    }
}
