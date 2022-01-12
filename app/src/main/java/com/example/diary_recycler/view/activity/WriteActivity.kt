package com.example.diary_recycler.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_recycler.databinding.ActivityMainBinding
import com.example.diary_recycler.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity(){

    private val binding: ActivityWriteBinding by lazy {
        ActivityWriteBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title_et= binding.titleBtn
        val content_et=binding.contentsBtn
        binding.back.setOnClickListener{
            finish()
        }
        binding.button.setOnClickListener {
            title_et.text
            //  database.
        }
    }}