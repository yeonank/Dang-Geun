package com.example.diary_recycler.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_recycler.databinding.ActivityChatBinding
import com.example.diary_recycler.databinding.ActivityWriteBinding

class ChatActivity : AppCompatActivity(){

    private val binding: ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            finish()
        }

    }}