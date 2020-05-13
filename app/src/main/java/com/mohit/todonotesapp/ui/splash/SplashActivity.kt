package com.mohit.todonotesapp.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkLoginStatus()
    }

    private fun checkLoginStatus() {

    }
}