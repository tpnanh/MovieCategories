package com.example.moviecategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.moviecategories.ui.AddCategoriesActivity
import com.example.moviecategories.ui.MoviesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
    }

    private fun setUpView() {
        //navigate to Add Categories Activity
        btn_add.setOnClickListener(this)
        //navigate to Movies Activity
        btn_show.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            btn_add -> {
                startActivity(AddCategoriesActivity.newIntent(this))
                finish()
            }
            btn_show -> {
                startActivity(MoviesActivity.newIntent(this))
                finish()
            }
        }
    }
}