package com.mohit.todonotesapp.ui.MyNotes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R

class MyNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)

        val intent = this.intent
        Log.d("IntentDataPass", intent.getStringExtra("full_name"))
    }
}