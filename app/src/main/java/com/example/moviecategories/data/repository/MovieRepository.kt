package com.example.moviecategories.data.repository

import android.app.Application
import com.example.moviecategories.data.local.getDatabase
import com.example.moviecategories.data.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val application: Application) {

    suspend fun insertMovie(category: String, movie: String) {
        withContext(Dispatchers.IO){
            var myMovie: MovieEntity = MovieEntity(0,"","")
            myMovie.nameCategory = category
            myMovie.nameMovie = movie
            getDatabase(application).dao.insertMovie(myMovie)
        }
    }


}