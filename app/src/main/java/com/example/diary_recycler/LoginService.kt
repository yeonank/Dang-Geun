package com.example.diary_recycler

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("/join/login")
    fun userLogin(
        @Field("userToken") userToken:String,
        @Field("userEmail") userEmail:String,
        @Field("userName") userName:String,
        @Field("profileImg") profileImg:String
        /*@Field("userid") userid:String,
        @Field("userpw") userpw:String*/

    ) : Call<ResponseDC>

    //fun userLogin(@Body data: LoginData) : Call<Login>
}