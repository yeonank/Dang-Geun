package com.example.diary_recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_recycler.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity(){

    private val binding: ActivityPostBinding by lazy {
        ActivityPostBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.back.setOnClickListener{
            finish()
        }
        binding.button2.setOnClickListener {

        }
    }}