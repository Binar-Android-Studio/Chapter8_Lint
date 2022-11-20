package com.example.challangechapter6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challangechapter6.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var  sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregister.setOnClickListener {
            val name        = binding.name.text.toString()
            val username    = binding.username.text.toString()
            val email       = binding.email.text.toString()
            val pass1       = binding.password.text.toString()
            val pass2       = binding.password2.text.toString()
            val phone       = "+91 7999193854"
            val aboutme     = "Doing at the sweet spot between aesthetics and elegance to give life to your brand.My opinion belong to nobody but myself. "
            val address     = "Melirang Wetan Bungah Gresik"

            sharedPreferences = getSharedPreferences("registerData", Context.MODE_PRIVATE)

            if(pass1 == pass2){
                val addData = sharedPreferences.edit()
                addData.putString("name", name)
                addData.putString("username", username)
                addData.putString("email", email)
                addData.putString("pass", pass1)
                addData.putString("phone", phone)
                addData.putString("aboutme", aboutme)
                addData.putString("address", address)

                addData.apply()
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()

                val move = Intent(this, LoginActivity::class.java)
                startActivity(move)
            }
            else
                Toast.makeText(this, "Password Yang Dimasukkan Tidak Sama", Toast.LENGTH_SHORT).show()


        }


        binding.logintriger.setOnClickListener {
            val move = Intent(this, LoginActivity::class.java)
            startActivity(move)
        }

    }
}