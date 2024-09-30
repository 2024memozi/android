package com.memozi.user

import com.memozi.auth.model.request.RequestSignInDto
import retrofit2.http.Body
import retrofit2.http.HTTP

interface UserService {
    @HTTP(method = "DELETE", path = "/deleteMember", hasBody = true)
    suspend fun delete(
        @Body accessToken: RequestSignInDto
    )
}
