package com.example.rickandmortyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {

        const val LOCATIONS_API = "location"
        const val EPISODES_API = "episode"
        const val EXTRA_CHARACTER = "character"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.imageButtonCharacters.setOnClickListener {
            val intent = Intent(this, CharactersActivity::class.java)
            startActivity(intent)
        }
        binding.imageButtonEpisode.setOnClickListener {
            val intent = Intent(this, EpisodesActivity::class.java)
            startActivity(intent)
        }
        binding.imageButtonLocations.setOnClickListener {
            val intent = Intent(this, LocationsActivity::class.java)
            startActivity(intent)
        }
    }
}
