package com.example.moviecategories.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moviecategories.data.local.getDatabase
import com.example.moviecategories.data.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val application: Application) {
    var getAllCategories : LiveData<List<CategoryEntity>> = getDatabase(application).dao.getAllCategory()

    suspend fun insertCategory(category: String) {
        withContext(Dispatchers.IO){
            var myCategory: CategoryEntity = CategoryEntity("")
            myCategory.nameCategory = category
            getDatabase(application).dao.insertCategory(myCategory)
        }
    }


}