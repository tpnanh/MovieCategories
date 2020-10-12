package com.example.moviecategories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecategories.R
import com.example.moviecategories.data.model.MovieEntity

class MoviesAdapter: ListAdapter<MovieEntity, MovieViewHolder>(DataDiffUtilMovie()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder{
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem (position)
        holder.bind(item)
    }
}

class DataDiffUtilMovie: DiffUtil.ItemCallback<MovieEntity>(){
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.nameMovie == newItem.nameMovie
    }
    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }
}

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
    val movieName = view.findViewById<TextView>(R.id.tv_movie_name)

    companion object{
        fun from(parent: ViewGroup): MovieViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)

            val view = layoutInflater.inflate(R.layout.list_movies_item,parent,false)

            return MovieViewHolder(view)
        }
    }

    fun bind(item: MovieEntity) {
        movieName.text = item.nameMovie
    }
}