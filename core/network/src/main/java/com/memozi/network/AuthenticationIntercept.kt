package com.memozi.network

import android.content.Context
import android.content.Intent
import com.memozi.common.buildconfig.BuildConfigFieldProvider
import com.memozi.datastore.token.TokenDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthenticationIntercept @Inject constructor(
    private val datastore: TokenDataStore,
    @ApplicationContext private val context: Context,
    private val buildConfigFieldProvider: BuildConfigFieldProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = handleRequest(originalRequest)
        val response = chain.proceed(authRequest)
        if (response.code == 200 && originalRequest.url.toString() == buildConfigFieldProvider.get().baseUrl) {
            sendUploadResultBroadcast("success")
        } else if (originalRequest.url.toString() == buildConfigFieldProvider.get().baseUrl) {
            sendUploadResultBroadcast(
                "업로드 실패했습니다."
            )
        }
        return response
    }

    private fun handleRequest(originalRequest: Request) = originalRequest.accessTokenBuilder()

    private fun Request.accessTokenBuilder() =
        this.newBuilder().addHeader(
            "Authorization",
            runBlocking { "Bearer ${datastore.token.first().accessToken}" }
        ).build()

    private fun sendUploadResultBroadcast(message: String) {
        val intent = Intent("com.example.UPLOAD_RESULT").apply {
            putExtra("message", message)
        }
        context.sendBroadcast(intent)
    }
}
