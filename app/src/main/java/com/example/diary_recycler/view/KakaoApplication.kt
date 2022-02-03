package com.example.diary_recycler.view

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "b0b0ad05500b939a4ae91c64848b80d3") //네이티브 앱 키
    }
}