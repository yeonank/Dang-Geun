package com.example.diary_recycler

import com.google.gson.annotations.SerializedName

public class LoginResponse {
    @SerializedName("code")
    private val code = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("userId")
    private val userId = 0

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun getUserId(): Int {
        return userId
    }
}