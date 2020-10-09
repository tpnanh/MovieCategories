package com.example.moviecategories.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.moviecategories.data.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class AddCategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val dataScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val categoryRepo: CategoryRepository = CategoryRepository(application)

    fun insertCategory(category: String) {
        dataScope.launch {
            categoryRepo.insertCategory(category)
        }

    }
}