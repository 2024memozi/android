package com.memozi.auth.api

import com.memozi.auth.model.request.RequestSignInDto
import com.memozi.auth.model.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthService {
    @POST("/oauth2/kakao/login")
    suspend fun signIn(
        @Body accessToken: RequestSignInDto
    ): ResponseSignInDto

    @DELETE("/deleteMember")
    suspend fun delete()
}
