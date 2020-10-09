package com.example.moviecategories.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecategories.data.model.CategoryEntity
import com.example.moviecategories.data.model.MovieEntity

@Dao
interface DaoService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categories: CategoryEntity)

    @Query("select * from categories_table ORDER BY nameCategory ASC")
    fun getAllCategory(): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)
//
//    @Query("select * from MovieEntity")
//    fun getAllMovies(): LiveData<MutableList<MovieEntity>>
//
//    @Query("select nameMovie from MovieEntity WHERE nameCategory = :category")
//    fun getMovieByCategory(category: String): List<MovieEntity>
}