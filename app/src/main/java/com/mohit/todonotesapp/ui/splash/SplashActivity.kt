package com.mohit.todonotesapp.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.ui.mynotes.MyNotesActivity
import com.mohit.todonotesapp.ui.login.LoginActivity
import com.mohit.todonotesapp.utils.common.PrefConstant

class SplashActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefs.getBoolean(PrefConstant.KEY_IS_LOGGED_IN, false)
        if (isLoggedIn) {
            val intent : Intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            Intent(this@SplashActivity, LoginActivity::class.java)?.run {
                startActivity(this)
            }
        }
    }

    private fun setupSharedPreference() {
        prefs = getSharedPreferences(PrefConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}