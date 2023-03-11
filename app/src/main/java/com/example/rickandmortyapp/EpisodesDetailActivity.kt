package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityEpisodesDetailBinding

class EpisodesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
