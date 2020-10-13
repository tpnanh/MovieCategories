package com.example.moviecategories.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecategories.data.model.CategoryEntity
import com.example.moviecategories.data.model.MovieEntity

@Database(entities = [MovieEntity::class, CategoryEntity::class],version = 3)
abstract class roomDatabase : RoomDatabase(){
    abstract val dao: DaoService
}

private lateinit var INSTANCE: roomDatabase
fun getDatabase(context: Context):roomDatabase{
    synchronized(roomDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                roomDatabase::class.java,
                "movieCategories")
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}