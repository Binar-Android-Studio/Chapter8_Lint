package com.example.challangechapter6.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.challangechapter6.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)


//        var getname = bundle?.getString("name").toString()
        val getname = intent.extras?.getString("name")
        val getpopularity = intent.extras?.getString("popularity")
        val getimagelink = intent.extras?.getString("imagelink")
        val getdesc = intent.extras?.getString("desc")

        Glide.with(this).load(getimagelink).into(binding.ivPoster)
        binding.title.text = getname
        binding.popularity.text = getpopularity
        binding.overview.text = getdesc


        setContentView(binding.root)
    }
}