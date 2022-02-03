package com.example.diary_recycler.view

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "81fbf5e64c216ddccc679cbaf3a64871") //네이티브 앱 키
    }
}