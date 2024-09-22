package com.memozi.oauth.repository

import android.content.Context
import android.util.Log
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.memozi.oauth.OAuthInteractor
import com.memozi.oauth.model.KakaoToken
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoAuthManager @Inject constructor(
    private val client: UserApiClient,
    @ActivityContext private val context: Context
) : OAuthInteractor {
    override suspend fun loginByKakao(): Result<KakaoToken> =
        suspendCancellableCoroutine {
            when (client.isKakaoTalkLoginAvailable(context)) {
                true -> {
                    client.loginWithKakaoTalk(context) { token, error ->
                        Log.d("카카오", "loginByKakao: 카톡 $token")
                        if (error != null) {
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }
                            client.loginWithKakaoAccount(context) { accountToken, accountError ->
                                if (accountError != null) {
                                    it.resume(Result.failure(accountError))
                                    return@loginWithKakaoAccount
                                }
                                if (accountToken != null) {
                                    it.resume(
                                        Result.success(
                                            KakaoToken(
                                                accountToken.accessToken,
                                                accountToken.refreshToken
                                            )
                                        )
                                    )
                                    return@loginWithKakaoAccount
                                }
                            }
                        } else if (token != null) {
                            it.resume(
                                Result.success(
                                    KakaoToken(
                                        token.accessToken,
                                        token.refreshToken
                                    )
                                )
                            )
                            return@loginWithKakaoTalk
                        }
                    }
                }

                false -> {
                    client.loginWithKakaoAccount(context) { token, error ->
                        Log.d("카카오", "loginByKakao: 카톡 $token")
                        if (error != null) {
                            it.resume(Result.failure(error))
                            return@loginWithKakaoAccount
                        }
                        if (token != null) {
                            it.resume(
                                Result.success(
                                    KakaoToken(
                                        token.accessToken,
                                        token.refreshToken
                                    )
                                )
                            )
                            return@loginWithKakaoAccount
                        }
                        it.resumeWithException(Throwable("Unreachable code"))
                    }
                }
            }
        }

    override fun logout() {
        client.logout(Timber::e)
    }

    override fun withdraw() {
        client.unlink(Timber::e)
    }

    override suspend fun getUser(): String {
        return suspendCancellableCoroutine { continuation ->
            client.me { user, error ->
                if (error != null) {
                    Log.e("카카오로그인 test", "사용자 정보 요청 실패", error)
                    continuation.resumeWithException(error)
                } else if (user != null) {
                    val nickname = user.kakaoAccount?.profile?.nickname ?: ""
                    continuation.resume(nickname)
                }
            }
        }
    }
}
