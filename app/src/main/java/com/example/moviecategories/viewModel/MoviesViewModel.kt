package com.example.moviecategories.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecategories.data.model.MovieEntity
import com.example.moviecategories.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : AndroidViewModel(application)  {
    private var viewModelJob = Job()
    val dataScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val movieRepo: MovieRepository = MovieRepository(application)

    var getAllMovies: LiveData<List<MovieEntity>> = movieRepo.getAllMovies

    private var _getMovieByCategory = MutableLiveData<List<MovieEntity>>()
    val getMovieByCategory: LiveData<List<MovieEntity>>
        get() = _getMovieByCategory

    fun getMovieByCategory(category: String){
        dataScope.launch {
            if (movieRepo.getMovieByCategory(category) != null){
                _getMovieByCategory.value = movieRepo.getMovieByCategory(category)
            }
        }
    }
}
