package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
import java.util.prefs.Preferences

class MoviesActivity: AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener  {
    private lateinit var arrayCategories: ArrayList<String>
    private lateinit var spinnerText: String

    private lateinit var data : List<MovieEntity>

    private val mSharedPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(this)

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var addMovieViewModel: AddMovieViewModel

    private lateinit var adapterMovie: MoviesAdapter

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, MoviesActivity::class.java)
        }
        const val SAVE_INFORMATION = "SAVE_INFORMATION"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setUpView()
        setUpViewModel()
    }

    private fun setUpView() {
        arrayCategories = ArrayList()


        if (!getPreferences().isNullOrEmpty() && !getPreferences()!!.contains("Choose category")){
            getPreferences()?.let { arrayCategories.add(it) }
            arrayCategories.add("Choose category")
        }
        else{
            arrayCategories.add("Choose category")
        }

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
                    if (!getPreferences()!!.contains(it[i].nameCategory)) {
                        arrayCategories.add(it[i].nameCategory)
                    }
                }
            }
        })

        moviesViewModel.getAllMovies.observe(this, Observer { it ->
            data = it
        })

        moviesViewModel.getMovieByCategory.observe(this, Observer { it ->
            val sorted = it.filter { item -> item.nameMovie.contains(et_movie_name.text.toString())  }
            adapterMovie.submitList(sorted)
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        spinnerText = text
        savePreferences(spinnerText)
    }

    fun getPreferences(): String?{
        return mSharedPreferences.getString(SAVE_INFORMATION,null)
    }

    fun savePreferences(spinner: String){
        mSharedPreferences.edit().putString(SAVE_INFORMATION,spinner).apply()
    }

    override fun onClick(view: View?) {
        when (view){
            btn_search -> onSearchResult()
        }
    }

    private fun onSearchResult(){
        if (spinnerText == "Choose category" && et_movie_name.text.toString() == ""){
            adapterMovie.submitList(data)
        }
        else if (spinnerText != "Choose category" && et_movie_name.text.toString() == ""){
            moviesViewModel.getMovieByCategory(spinnerText)
        }
        else {
            moviesViewModel.getMovieByCategory(spinnerText)
        }
    }
}