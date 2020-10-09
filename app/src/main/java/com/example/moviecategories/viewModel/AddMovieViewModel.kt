package com.example.moviecategories.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviecategories.data.model.CategoryEntity
import com.example.moviecategories.data.repository.CategoryRepository
import com.example.moviecategories.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddMovieViewModel(application: Application) : AndroidViewModel(application)  {
    private var viewModelJob = Job()
    private val dataScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val categoryRepo: CategoryRepository = CategoryRepository(application)
    private val movieRepo: MovieRepository = MovieRepository(application)

    var getAllCategories: LiveData<List<CategoryEntity>> = categoryRepo.getAllCategories

    fun insertMovie(category: String, movie: String){
        dataScope.launch {
            movieRepo.insertMovie(category,movie)
        }
    }

}