package com.example.diary_recycler.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diary_recycler.APIInterface
import com.example.diary_recycler.ResponseDC
import com.example.diary_recycler.databinding.FragmentTestBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestFragment: Fragment() {

    private val binding : FragmentTestBinding by lazy {
        FragmentTestBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        initTest()
        return binding.root
    }

    fun initTest(){
        val url = "http://54.180.87.148:52300"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var server = retrofit.create(APIInterface::class.java)

        binding.button3.setOnClickListener{
            Log.e("testFragment btn3", "start")

            server.getRequest("name").enqueue(object: Callback<ResponseDC> {
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                    Log.e("testFragment btn3 fail", "error:(${t.message})")
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())

                    Log.e("testFragment btn3", "success")
                }

            })
            Log.e("testFragment btn3", "end")
        }

        binding.button4.setOnClickListener{
            server.postRequest("id", "password").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }

        binding.button5.setOnClickListener {
            server.putRequest("board", "내용").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }

        binding.button6.setOnClickListener{
            server.deleteRequest("board").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }


    }
}