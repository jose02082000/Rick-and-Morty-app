package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var charactersList: MutableList<CharactersResultModel?>
    private var isLoading = false
    private val utils = Utils()
    private val charactersViewModel by lazy { CharactersViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initRecyclerView() {
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

    private fun initScrollListener() {
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == charactersList.size - 1) {
                        // Fin de la lista
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        charactersList.add(null)
        binding.rvCharacters.adapter?.notifyItemInserted(charactersList.size - 1)
        val handler = Handler()
        handler.postDelayed(
            {
                charactersList.removeAt(charactersList.size - 1)
                val scrollPosition: Int = charactersList.size
                binding.rvCharacters.adapter?.notifyItemRemoved(scrollPosition)
                var currentSize = scrollPosition
                val nextLimit = currentSize + 10
                while (currentSize - 1 < nextLimit) {
                    // TODO -> Call service in page 2 to add the items
                }
                binding.rvCharacters.adapter?.notifyDataSetChanged()
                isLoading = false
            }, 2000
        )
    }

    private fun initialize() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            charactersList = charactersViewModel.getCharactersList()
            runOnUiThread {
                if (charactersList.isNotEmpty()) {
                    initRecyclerView()
                    initScrollListener()
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
