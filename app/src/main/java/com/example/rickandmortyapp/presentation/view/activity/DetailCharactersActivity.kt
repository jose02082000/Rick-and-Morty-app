package com.example.rickandmortyapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.rickandmortyapp.databinding.ActivityDetailcharactersBinding
import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.presentation.viewmodel.detail.DetailCharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCharactersActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailcharactersBinding
    private val detailCharacterViewModel by lazy { DetailCharacterViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailcharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getCharacterInformation(id)
    }

    private fun getCharacterInformation(id: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val characterDetail = detailCharacterViewModel.getDetailCharacters(id)
            runOnUiThread {
                creteUi(characterDetail)
                binding.progressBar.isVisible = false
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
            tvOriginCharacterDetail.text = character.origin?.name
        }
    }
}
