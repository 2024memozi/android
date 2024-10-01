package com.memozi.auth.source.remote

interface UserRemoteDataSource {
    suspend fun delete(acessToken:String)
}
