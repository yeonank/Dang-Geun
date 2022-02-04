package com.example.diary_recycler.view

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "b6ab767025be19ef9c317db1b201e072") //네이티브 앱 키
    }
}