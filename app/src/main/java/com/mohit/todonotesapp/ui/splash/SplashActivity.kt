package com.mohit.todonotesapp.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.ui.mynotes.MyNotesActivity
import com.mohit.todonotesapp.ui.login.LoginActivity
import com.mohit.todonotesapp.utils.common.PrefConstant

class SplashActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    val TAG = "Firebase Token"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
        getFirebaseToken()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefs.getBoolean(PrefConstant.KEY_IS_LOGGED_IN, false)
        if (isLoggedIn) {
            val intent: Intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
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

    private fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registratin token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                Log.d(TAG, "token values is : $token")
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            }
    }
}