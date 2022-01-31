package com.example.diary_recycler.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.TextUtils
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
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(){
    lateinit var chatAdapter: ChatAdapter
    
    internal lateinit var preferences: SharedPreferences//사용자 이름을 이걸로 저장할까..?

    private var hasConnection: Boolean = false
    private var thread2: Thread? = null
    private var startTyping = false
    private var time = 2

    private var mSocket: Socket = IO.socket("http://3.36.48.206:52207")


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

        if (savedInstanceState != null) {
            hasConnection = savedInstanceState.getBoolean("hasConnection")
            Log.e("has connection? ", "not null")
        }

        if (hasConnection) {

        } else {
            Log.e("소켓 연결 시작 ", "not null")
            //소켓연결
            var socket = mSocket.connect()


            //서버에 신호 보내는거같음 밑에 에밋 리스너들 실행
            //socket.on은 수신
            mSocket.on("connect user", onNewUser)
            mSocket.on("chat message", onNewMessage)

            val userId = JSONObject()
            try {
                userId.put("username", preferences.getString("name", "") + " Connected")
                userId.put("roomName", "room_example")
                Log.e("username",preferences.getString("name", "") + " Connected")

                //socket.emit은 메세지 전송임
                mSocket.emit("connect user", userId)//이거 하면 서버에 반응 떠야함
                Log.e("hahah", socket.connected().toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        hasConnection = true
        //뭔가 설정..?
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.navigationIcon?.apply {
            setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)
        }

        //전송버튼
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

    internal var onNewMessage: Emitter.Listener = Emitter.Listener { args ->
        runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val name: String
            val script: String
            val profile_image: String
            val date_time: String
            try {
                Log.e("asdasd", data.toString())
                name = data.getString("name")
                script = data.getString("script")
                profile_image = data.getString("profile_image")
                date_time = data.getString("date_time")


                val format = ChatModel(name, script, profile_image, date_time)
                chatAdapter.addItem(format)
                chatAdapter.notifyDataSetChanged()
                Log.e("new me",name )
            } catch (e: Exception) {
                return@Runnable
            }
        })
    }

    //어플 키자마자 서버에  connect user 하고 프로젝트에 on new user 실행
    internal var onNewUser: Emitter.Listener = Emitter.Listener { args ->
        runOnUiThread(Runnable {
            val length = args.size

            if (length == 0) {
                return@Runnable
            }
            //Here i'm getting weird error..................///////run :1 and run: 0
            var username = args[0].toString()
            try {
                val `object` = JSONObject(username)
                username = `object`.getString("username")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        })
    }


    fun sendMessage() {//어댑터에 메시지 전송
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        //판매자 이름 가져오기(intent)
        //val name = intent.getStringExtra("seller_name")//인텐트 or preferences

        //example에는 원래는 이미지 url이 들어가야할 자리

        /*val item = preferences.getString("name","")
            ?.let { ChatModel(it,binding.messageActivityEditText.text.toString(),"example", getTime) }
        if (item != null) {
            chatAdapter.addItem(item)//리스트에 메시지 넣기
        }*/

        val message = binding.messageActivityEditText.text.toString().trim({ it <= ' ' })
        if (TextUtils.isEmpty(message)) {
            return
        }
        binding.messageActivityEditText.setText("")
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", preferences.getString("name", ""))
            jsonObject.put("script", message)
            jsonObject.put("profile_image", "example")
            jsonObject.put("date_time", getTime)
            jsonObject.put("roomName", "room_example")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.e("챗룸", "sendMessage: 1" + mSocket.emit("chat message", jsonObject))
        preferences.getString("name", "")?.let { Log.e("sendmmm", it) }

        Log.e("editText: ", binding.messageActivityEditText.text.toString())
        chatAdapter.notifyDataSetChanged()
        //채팅 입력창 초기화
            }

    /*fun socketConnection(){
    }*/

}