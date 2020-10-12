package com.example.moviecategories.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moviecategories.data.local.getDatabase
import com.example.moviecategories.data.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val application: Application) {

    var getAllMovies : LiveData<List<MovieEntity>> = getDatabase(application).dao.getAllMovies()

    suspend fun insertMovie(category: String, movie: String) {
        withContext(Dispatchers.IO){
            var myMovie: MovieEntity = MovieEntity(0,"","")
            myMovie.nameCategoryMovie = category
            myMovie.nameMovie = movie
            getDatabase(application).dao.insertMovie(myMovie)
        }
    }

    suspend fun getMovieByCategory(category: String): List<MovieEntity> {
        var myMovie: List<MovieEntity>
        withContext(Dispatchers.IO){
            myMovie = getDatabase(application).dao.getMovieByCategory(category)
        }
        return myMovie
    }
}