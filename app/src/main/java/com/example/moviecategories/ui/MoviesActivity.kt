package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecategories.R
import com.example.moviecategories.adapter.MoviesAdapter
import com.example.moviecategories.data.model.MovieEntity
import com.example.moviecategories.viewModel.AddMovieViewModel
import com.example.moviecategories.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.Locale.filter

class MoviesActivity: AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var arrayCategories: ArrayList<String>
    private lateinit var spinnerText: String

    private lateinit var data : List<MovieEntity>
    private lateinit var sort : List<MovieEntity>

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var addMovieViewModel: AddMovieViewModel

    private lateinit var adapterMovie: MoviesAdapter

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, MoviesActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setUpView()
        setUpViewModel()
    }

    private fun setUpView() {
        arrayCategories = ArrayList()
        arrayCategories.add("Choose category")

        spinner_movie.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayCategories)
        spinner_movie.onItemSelectedListener = this

        btn_search.setOnClickListener(this)

        adapterMovie = MoviesAdapter()
        rc_movies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rc_movies.adapter = adapterMovie
    }

    private fun setUpViewModel() {
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        addMovieViewModel = ViewModelProviders.of(this).get(AddMovieViewModel::class.java)
        addMovieViewModel.getAllCategories.observe(this, Observer { it ->
            if (it!=null) {
                for (i in 0..it.size-1){
                    arrayCategories.add(it[i].nameCategory)
                }
            }
        })

        moviesViewModel.getAllMovies.observe(this, Observer { it ->
            data = it
            sort = it.filter { item -> item.nameMovie.contains(et_movie_name.text.toString())  }
        })

        moviesViewModel.getMovieByCategory.observe(this, Observer { it ->
            val sorted = it.filter { item -> item.nameMovie.contains(et_movie_name.text.toString())  }
            adapterMovie.submitList(sorted)
        })
    }

    override fun onClick(view: View?) {
        when (view){
            btn_search -> onSearchResult()
        }
    }

    private fun onSearchResult(){
        if (spinnerText == arrayCategories[0] && et_movie_name.text.toString() == ""){
            adapterMovie.submitList(data)
        }
        else if (spinnerText != arrayCategories[0] && et_movie_name.text.toString() == ""){
            moviesViewModel.getMovieByCategory(spinnerText)
        }
        else if (spinnerText == arrayCategories[0] && et_movie_name.text.toString() != ""){
            adapterMovie.submitList(sort)
        }
        else{
            moviesViewModel.getMovieByCategory(spinnerText)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        spinnerText = text
    }
}