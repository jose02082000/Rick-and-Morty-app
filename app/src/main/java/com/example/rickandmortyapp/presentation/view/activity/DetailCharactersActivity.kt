package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.data.repository.network.ApiService
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

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getCharacterInformation(id)
    }

    private fun getCharacterInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val charactersDetail =
                getRetrofit().create(ApiService::class.java).getCharactersDetail(id)
            if (charactersDetail.body() != null) {
                runOnUiThread {
                    creteUi(charactersDetail.body()!!)
                }
            }
        }
    }

    private fun creteUi(character: CharactersResultModel) {
        Picasso.get().load(character.image).into(binding.ivImageCharacterDetail)
        with(binding) {
            tvNameCharacterDetail.text = character.name
            tvGenderCharacterDetail.text = character.gender
            tvCreateCharacterDetail.text = character.created
            tvSpecieCharacterDetail.text = character.species
            tvStatusCharacterDetail.text = character.status
            tvOriginCharacterDetail.text = character.origin.name
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
