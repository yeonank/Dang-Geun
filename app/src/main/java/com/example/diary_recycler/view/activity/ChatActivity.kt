package com.example.diary_recycler.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_recycler.ChatAdapter
import com.example.diary_recycler.ChatModel
import com.example.diary_recycler.SwipeAdapter
import com.example.diary_recycler.databinding.ActivityChatBinding
import com.example.diary_recycler.databinding.ActivityWriteBinding
import java.text.SimpleDateFormat
import java.util.*

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
        //어댑터 연결
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

        binding.messageActivityImageView.setOnClickListener{
            sendMessage()
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
    }

    fun sendMessage() {//어댑터에 메시지 전송
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        //판매자 이름 가져오기(intent)
        val name = intent.getStringExtra("seller_name")

        //example에는 원래는 이미지 url이 들어가야할 자리

        val item =
            name?.let { ChatModel(it, binding.messageActivityEditText.text.toString(), "example", getTime) }
        if (item != null) {
            chatAdapter.addItem(item)//리스트에 넣기
        }
        if (name != null) {
            chatAdapter.userName = name
        }

        Log.e("editText: ", binding.messageActivityEditText.toString())
        chatAdapter.notifyDataSetChanged()
        //채팅 입력창 초기화
        binding.messageActivityEditText.setText("")
    }

}