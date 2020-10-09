package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecategories.R
import com.example.moviecategories.viewModel.AddMovieViewModel
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovieActivity: AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var addMovieViewModel: AddMovieViewModel

    private lateinit var array: ArrayList<String>

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
        array = ArrayList()
        array.add("Choose category")

        spinnerText = ""

        spinner_add_movie.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        spinner_add_movie.onItemSelectedListener = this

        btn_add_movie.setOnClickListener(this)

    }

    private fun setUpViewModel() {
        addMovieViewModel = ViewModelProviders.of(this).get(AddMovieViewModel::class.java)

        addMovieViewModel.getAllCategories.observe(this, Observer { it ->
            if (it!=null) {
                for (i in 0..it.size-1){
                    array.add(it[i].nameCategory)
                }
            }
        })
    }


    override fun onClick(view: View?) {
        when(view){
            btn_add_movie -> {
                if (et_add_movie.text.toString() != "" &&  spinnerText != "Choose category") {
                    //
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
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        spinnerText = text
    }
}