package com.example.diary_recycler.view.activity

import android.content.Context
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
import io.socket.client.IO
import io.socket.client.Socket
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(){
    lateinit var chatAdapter: ChatAdapter
    
    internal lateinit var preferences: SharedPreferences//사용자 이름을 이걸로 저장할까..?

    private var hasConnection: Boolean = false
    private var thread2: Thread? = null
    private var startTyping = false
    private var time = 2

    private var mSocekt: Socket = IO.socket("")


    private val binding: ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        //어댑터 연결
        chatAdapter = ChatAdapter(this)//this?
        binding.messageActivityRecyclerview.adapter = chatAdapter
        binding.messageActivityRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)

        }
        binding.messageActivityRecyclerview.setHasFixedSize(true)

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
        val name = intent.getStringExtra("seller_name")//인텐트 or preferences

        //example에는 원래는 이미지 url이 들어가야할 자리

        val item = preferences.getString("name","")
            ?.let { ChatModel(it,binding.messageActivityEditText.text.toString(),"example", getTime) }
        if (item != null) {
            chatAdapter.addItem(item)//리스트에 메시지 넣기
        }
        /*if (name != null) {//shared preferences 대신 넣음
            chatAdapter.userName = name
        }*/

        Log.e("editText: ", binding.messageActivityEditText.text.toString())
        chatAdapter.notifyDataSetChanged()
        //채팅 입력창 초기화
        binding.messageActivityEditText.setText("")
    }

}