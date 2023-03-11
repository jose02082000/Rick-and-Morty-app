package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityDetailLocationsBinding

class DetailLocationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailLocationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
