package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecategories.MainActivity
import com.example.moviecategories.R
import com.example.moviecategories.viewModel.AddMovieViewModel
import kotlinx.android.synthetic.main.activity_add_movie.*
import java.lang.reflect.Field


class AddMovieActivity: AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var addMovieViewModel: AddMovieViewModel

    private lateinit var arrayCategories: ArrayList<String>
    private lateinit var spinnerText: String

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, AddMovieActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        setUpView()
        setUpViewModel()
    }

    private fun setUpView() {
        arrayCategories = ArrayList()
        arrayCategories.add("Choose category")

        spinner_add_movie.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayCategories)
        spinner_add_movie.onItemSelectedListener = this

        spinnerText = ""

        btn_add_movie.setOnClickListener(this)
        btn_finish.setOnClickListener(this)
    }

    private fun setUpViewModel() {
        addMovieViewModel = ViewModelProviders.of(this).get(AddMovieViewModel::class.java)

        addMovieViewModel.getAllCategories.observe(this, Observer { it ->
            if (it!=null) {
                for (i in 0..it.size-1){
                    arrayCategories.add(it[i].nameCategory)
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when(view){
            //click to add movie to room
            btn_add_movie -> addMovie()
            //navigate to main activity
            btn_finish -> {
                startActivity(MainActivity.newIntent(this))
                finish()
            }
        }
    }

    private fun addMovie(){
        if (et_add_movie.text.toString() != "" &&  spinnerText != arrayCategories[0]) {
            addMovieViewModel.insertMovie(
                spinnerText,
                et_add_movie.text.toString().trim()
            )
            Toast.makeText(this,"Movie was added", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Please fill in all fields!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        spinnerText = text
    }
}