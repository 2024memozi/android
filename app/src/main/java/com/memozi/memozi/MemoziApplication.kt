package com.memozi.memozi

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.memozi.buildconfig.BuildConfig.KAKAO_NATIVE_KEY
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MemoziApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setKakaoSdk()
        val keyHash = Utility.getKeyHash(this)
        Log.d("키해시", " $keyHash")
    }

    private fun setKakaoSdk() {
        KakaoSdk.init(this, KAKAO_NATIVE_KEY)
    }
}
