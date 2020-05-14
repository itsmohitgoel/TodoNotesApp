package com.mohit.todonotesapp.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mohit.todonotesapp.R
import com.mohit.todonotesapp.ui.MyNotes.MyNotesActivity
import com.mohit.todonotesapp.utils.common.Constants
import com.mohit.todonotesapp.utils.common.PrefConstant
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextFullName: EditText
    private lateinit var editTextUserName: EditText
    private lateinit var buttonLogin: Button
    private lateinit var prefs: SharedPreferences

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupSharedPrefences()

        bindViews()
    }

    private fun bindViews() {
        editTextFullName = etFullName
        editTextUserName = etFullName
        this.buttonLogin = btnLogin

        this.buttonLogin.setOnClickListener {
            Log.d("Click", "onClick performed")
            val fullName = editTextFullName.text.toString()
            val lastName = editTextUserName.text.toString()

            if (fullName.isNotEmpty() && lastName.isNotEmpty()) {
                val intent = Intent(this@LoginActivity, MyNotesActivity::class.java).apply {
                    putExtra(Constants.FULL_NAME, fullName)
                }
                saveLoginStatus()
                saveFullName(fullName)

                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "FullName and UserName can't be empty", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun saveLoginStatus() {
        prefs.edit().putBoolean(PrefConstant.KEY_IS_LOGGED_IN, true).apply()
    }

    private fun saveFullName(fullName: String) {
        prefs.edit().putString(PrefConstant.KEY_FULLNAME, fullName).apply()
    }

    private fun setupSharedPrefences() {
        prefs = getSharedPreferences(PrefConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}
