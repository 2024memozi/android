package com.memozi.buildconfig

import com.memozi.buildconfig.BuildConfig.BASE_URL
import com.memozi.buildconfig.BuildConfig.DEBUG
import com.memozi.buildconfig.BuildConfig.KAKAO_NATIVE_KEY
import com.memozi.common.buildconfig.BuildConfigFieldProvider
import com.memozi.common.buildconfig.BuildConfigFields

class BuildConfigFieldsProviderImpl : BuildConfigFieldProvider {
    override fun get(): BuildConfigFields =
        BuildConfigFields(
            baseUrl = BASE_URL,
            kakaoNativeKey = KAKAO_NATIVE_KEY,
            isDebug = DEBUG
        )
}
