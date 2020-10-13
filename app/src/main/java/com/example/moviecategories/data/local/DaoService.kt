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

    @Query("select * from movie_table ORDER BY nameMovie ASC")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("select * from movie_table,categories_table WHERE nameCategory = :category and nameCategoryMovie = nameCategory ")
    fun getMovieByCategory(category: String): List<MovieEntity>
}