package com.example.diary_recycler

import com.google.gson.annotations.SerializedName

public class LoginData {
    @SerializedName("userToken")
    var userToken: String? = null

    @SerializedName("userEmail")
    var userEmail: String? = null

    @SerializedName("userName")
    var userName: String? = null

    @SerializedName("profileImg")
    var profileImg: String? = null

    fun LoginData(userToken: String?, userEmail: String?, userName: String?, profileImg: String?) {
        this.userToken = userToken
        this.userEmail = userEmail
        this.userName = userName
        this.profileImg = profileImg
    }
}