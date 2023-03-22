package com.example.rickandmortyapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.Utils
import com.example.rickandmortyapp.databinding.ActivityEpisodesBinding
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.presentation.view.adapter.EpisodesAdapter
import com.example.rickandmortyapp.presentation.viewmodel.EpisodesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityEpisodesBinding
    private lateinit var adapter: EpisodesAdapter
    private val utils = Utils()

    /**
     * para comenzar entonces creamos el valor del view model y lo instanciamos como by lazy al interior de las llaves debemos de colocar el viewmodel
     * que tenemos o que vamos a crear para seguir con el proceso, el archivo de view model se crea en la capa de presentacion en el paquete de viewmodel
     */
    private val episodesViewModel by lazy { EpisodesViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initRecyclerView(episodeList: List<EpisodesResultModel>) {
        with(binding) {
            rvEpisodes.setHasFixedSize(true)
            adapter = EpisodesAdapter(episodeList) { navigateToEpisodesDetails(it) }
            val layoutManager = LinearLayoutManager(this@EpisodesActivity)
            rvEpisodes.adapter = adapter
            rvEpisodes.layoutManager = layoutManager
            progressBar.isVisible = false
        }
    }

    private fun initialize() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val episodesList = episodesViewModel.getEpisodesList()
            runOnUiThread {
                if (episodesList.isNotEmpty()) {
                    initRecyclerView(episodesList)
                } else {
                    binding.progressBar.isVisible = false
                    utils.showToastOnError(
                        applicationContext,
                        getString(R.string.toast_episodes),
                    )
                }
            }
        }
    }

    private fun navigateToEpisodesDetails(id: String) {
        val intent = Intent(this, DetailEpisodesActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
