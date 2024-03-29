package com.example.rickandmortyapp.core

import android.content.Context
import android.widget.Toast

class Utils {
    fun showToastOnError(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT,
        ).show()
    }
}
