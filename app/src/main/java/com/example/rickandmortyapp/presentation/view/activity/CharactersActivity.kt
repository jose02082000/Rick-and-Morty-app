package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.Utils
import com.example.rickandmortyapp.databinding.ActivityCharactersBinding
import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.presentation.view.adapter.CharactersAdapters
import com.example.rickandmortyapp.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var adapter: CharactersAdapters
    private val utils = Utils()
    private val charactersViewModel by lazy { CharactersViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initRecyclerView(charactersList: List<CharactersResultModel>) {
        with(binding) {
            rvCharacters.setHasFixedSize(true)
            adapter = CharactersAdapters(charactersList) {
                navigateToDetailCharacter(it)
            }
            rvCharacters.adapter = adapter
            val manager = GridLayoutManager(this@CharactersActivity, 2)
            rvCharacters.layoutManager = manager

            progressBar.isVisible = false
        }
    }

    private fun initialize() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val charactersList = charactersViewModel.getCharactersList()
            runOnUiThread {
                if (charactersList.isNotEmpty()) {
                    initRecyclerView(charactersList)
                } else {
                    binding.progressBar.isVisible = false
                    utils.showToastOnError(
                        applicationContext,
                        getString(R.string.toast_character),
                    )
                }
            }
        }
    }

    private fun navigateToDetailCharacter(id: String) {
        val intent = Intent(this, DetailCharactersActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
