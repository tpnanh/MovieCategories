package com.example.moviecategories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.moviecategories.R
import com.example.moviecategories.viewModel.AddCategoriesViewModel
import kotlinx.android.synthetic.main.activity_add_categories.*

class AddCategoriesActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var addCategoriesViewModel: AddCategoriesViewModel
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, AddCategoriesActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_categories)

        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        addCategoriesViewModel = ViewModelProviders.of(this).get(AddCategoriesViewModel::class.java)
    }

    private fun setUpView() {
        //navigate to Add Movie Activity
        btn_add_categories_movie.setOnClickListener(this)
        //add category to room
        btn_add_categories.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            btn_add_categories_movie -> {
                startActivity(AddMovieActivity.newIntent(this))
                finish()
            }
            btn_add_categories -> {
                if (et_movie_categories.text.toString() != "") {
                    addCategoriesViewModel.insertCategory(
                        et_movie_categories.text.toString().trim()
                    )
                    Toast.makeText(this,"Category was added",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}