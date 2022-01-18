package com.example.diary_recycler.view.activity

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_recycler.ChatAdapter
import com.example.diary_recycler.SwipeAdapter
import com.example.diary_recycler.databinding.ActivityChatBinding
import com.example.diary_recycler.databinding.ActivityWriteBinding

class ChatActivity : AppCompatActivity(){
    lateinit var chatAdapter: ChatAdapter
    private val binding: ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        chatAdapter = ChatAdapter(this)//this?
        binding.messageActivityRecyclerview.adapter = chatAdapter

        binding.messageActivityRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)

        }

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        binding.toolbar.navigationIcon?.apply {

            setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }}