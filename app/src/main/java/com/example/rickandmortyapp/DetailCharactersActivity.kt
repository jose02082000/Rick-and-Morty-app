package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityDetailcharactersBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailCharactersActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailcharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailcharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_ID).orEmpty()
        getCharacterInformation(name)
    }

    private fun getCharacterInformation(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val charactersDetail =
                getRetrofit().create(ApiService::class.java).getCharactersDetail(name)
            if (charactersDetail.body() != null) {
                runOnUiThread {
                    creteUi(charactersDetail.body()!!)
                }
            }
        }
    }

    private fun creteUi(character: CharactersResultModel) {
        Picasso.get().load(character.image).into(binding.ivImageCharacterDetail)
        binding.tvNameCharacterDetail.text = character.name
        binding.tvGenderCharacterDetail.text = character.gender
        binding.tvCreateCharacterDetail.text = character.created
        binding.tvSpecieCharacterDetail.text = character.species
        binding.tvStatusCharacterDetail.text = character.status
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
