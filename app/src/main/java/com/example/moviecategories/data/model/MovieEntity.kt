package com.example.moviecategories.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    var idMovie: Int,
    var nameCategoryMovie: String,
    var nameMovie: String
)