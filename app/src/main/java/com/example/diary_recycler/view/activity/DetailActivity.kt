package com.example.diary_recycler.view.activity

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_recycler.SqliteHelper
import com.example.diary_recycler.WriteData
import com.example.diary_recycler.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity(){
    var helper: SqliteHelper? = null
    var data : WriteData ?= null


    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val idx = intent.getIntExtra("id",0)
        helper = SqliteHelper(this, "article", null, 1)

        data= helper?.selectArticle()?.get(idx)
        binding.textView4.setText(data?.title)
        binding.textView5.setText(data?.content)
        binding.back.setOnClickListener{
            finish()
        }
        binding.menu.setOnClickListener{

        }
        binding.button2.setOnClickListener {

        }
    }}