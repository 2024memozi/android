package com.memozi.user.datasource

import com.memozi.auth.model.request.RequestSignInDto
import com.memozi.auth.source.remote.UserRemoteDataSource
import com.memozi.user.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun delete(acessToken: String) {
        userService.delete( RequestSignInDto(accessToken = acessToken))
    }
}
