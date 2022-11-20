package com.example.challangechapter6.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.challangechapter6.databinding.ActivitySplashscreenBinding


@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = getSharedPreferences("registerData", Context.MODE_PRIVATE)
        val username = data.getString("usernamelgn", null)
        val pass = data.getString("passwordlgn", null)

        Handler(Looper.myLooper()!!).postDelayed({
            if(username != null && pass != null)
                startActivity(Intent(this, MainActivity::class.java))
            else
                startActivity(Intent(this, LoginActivity::class.java))
        },500)
    }
}