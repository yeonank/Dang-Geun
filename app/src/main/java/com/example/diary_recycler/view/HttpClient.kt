package com.example.diary_recycler.view

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public class HttpClient {
    companion object {
        //통신할 서버 url
        private const  val url = "http://13.124.154.227:52970"

        //Retrofit 객체 초기화
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(this.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            //   val test: RetrofitApi = retrofit.create(RetrofitApi::class.java)
    }
}