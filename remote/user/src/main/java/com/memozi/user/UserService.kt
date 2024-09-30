package com.memozi.user

import retrofit2.http.DELETE

interface UserService {
    @DELETE("/deleteMember")
    suspend fun delete()
}
