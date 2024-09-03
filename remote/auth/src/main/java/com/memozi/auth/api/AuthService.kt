package com.memozi.auth.api

import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("/oauth2/authorization/kakao")
    suspend fun signIn(
        @Query("code") code: String
    ): String
}
