package com.example.challangechapter6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challangechapter6.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var  sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            sharedPrefs = getSharedPreferences("registerData", Context.MODE_PRIVATE)

            val usernameData = sharedPrefs.getString("username", null)
            val passwordData = sharedPrefs.getString("pass", null)

            val usernamelgn = binding.username.text.toString()
            val passwordlgn = binding.password.text.toString()

            if (usernamelgn == usernameData && passwordData == passwordlgn){
                val addData = sharedPrefs.edit()
                addData.putString("usernamelgn", usernamelgn)
                addData.putString("passwordlgn", passwordlgn)
                addData.apply()
                Toast.makeText(this, "Login Sucsessfull", Toast.LENGTH_SHORT).show()

                val move = Intent(this, MainActivity::class.java)
                startActivity(move)
            }
            else
                Toast.makeText(this, "Username Dan Password Tidak Cocok !!", Toast.LENGTH_SHORT).show()

        }

        binding.register.setOnClickListener {
            val move = Intent(this, RegisterActivity::class.java)
            startActivity(move)
        }
    }
}