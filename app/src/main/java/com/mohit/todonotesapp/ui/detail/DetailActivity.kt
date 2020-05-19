package com.mohit.todonotesapp.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.utils.common.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var textViewDetailTitle : TextView
    lateinit var textViewDetailDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        bindView()

        val title = intent.getStringExtra(Constants.TITLE)
        val description = intent.getStringExtra(Constants.DESCRIPTION)
        textViewDetailTitle.text = title
        textViewDetailDescription.text = description
    }

    private fun bindView() {
        textViewDetailTitle = tvDetailTitle
        textViewDetailDescription = tvDetailDescription
    }

}