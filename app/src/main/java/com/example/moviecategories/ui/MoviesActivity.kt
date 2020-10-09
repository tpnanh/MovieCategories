package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecategories.R

class MoviesActivity: AppCompatActivity() {
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, MoviesActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

    }
}