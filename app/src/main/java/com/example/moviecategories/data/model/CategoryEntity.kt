package com.example.moviecategories.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoryEntity (
    @PrimaryKey()
    var nameCategory: String
)