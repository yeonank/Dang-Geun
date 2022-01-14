package com.example.diary_recycler.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_recycler.R
import com.example.diary_recycler.SqliteHelper
import com.example.diary_recycler.SwipeAdapter
import com.example.diary_recycler.WriteData
import com.example.diary_recycler.databinding.ActivityMainBinding
import com.example.diary_recycler.databinding.ActivityWriteBinding
import com.example.diary_recycler.view.fragment.HomeFragment

final class WriteActivity() : AppCompatActivity(){
    var helper:SqliteHelper? = null

    private val binding: ActivityWriteBinding by lazy {
        ActivityWriteBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.e("writeactivity.oncreate", "1")

        binding.back.setOnClickListener{
            finish()
        }

        val title_et= binding.titleBtn
        val content_et=binding.contentsBtn

        //데이터 homeFragment로 전송
        binding.button.setOnClickListener {
            if(content_et.text.toString().isNotEmpty()){
                Log.e("writeActivity.send", "data sending start")

                val bundle = Bundle()
                bundle.putString("content", content_et.text.toString())
                bundle.putString("title", title_et.text.toString())
                val home = HomeFragment()

                home.swipeadapter = SwipeAdapter(this)
                home.helper = SqliteHelper(this, "article", null, 1)
                home.arguments = bundle
                //val transaction = supportFragmentManager.beginTransaction()
               // transaction.add(R.id.frame_layout, home)
               // transaction.commit()

                home.setArticle()

                Log.e("writeActivity.send", "data sending end")
            }

            finish()
          //  val nextIntent = Intent(this, MainActivity::class.java)
          //  startActivity(nextIntent)

        }

    }}