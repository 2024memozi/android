package com.memozi.login

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.designsystem.Kakao
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current as ComponentActivity
    val entryPoint = EntryPointAccessors.fromActivity<OAuthEntryPoint>(context)
    val oAuthInteractor = entryPoint.getOAuthInteractor()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideeffect ->
            when (sideeffect) {
                is LoginSideEffect.LoginError -> TODO()
                LoginSideEffect.LoginSuccess -> {}
                LoginSideEffect.LoginToSignUp -> TODO()
                LoginSideEffect.StartLogin -> {
                    oAuthInteractor.loginByKakao().onSuccess {
                        Log.d("카카카오 메인", "LoginRoute: $it")
                        viewModel.signIn(it.accessToken)
                    }
                }
            }
        }
    }
    LoginScreen { viewModel.startKakaoLogin() }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(223f)
        )
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "로고에용"
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(text = "MEMO : Zi", style = MemoziTheme.typography.appnameBold24)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(320f)
        )
    }
}

@Composable
fun LoginScreen(kakaoButtonEvent: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MemoziTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(189f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_memozi_title),
            contentDescription = "타이틀이에용~"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "로고에용~",
            modifier = Modifier
                .fillMaxWidth(0.28f)
                .aspectRatio(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(256f)
        )
        Button(
            onClick = { kakaoButtonEvent() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Kakao),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_kakako),
                    contentDescription = "나는 카카오~"
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "카카오로 로그인",
                    style = MemoziTheme.typography.ngBold15,
                    color = MemoziTheme.colors.loginBlack
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(50f)
        )
    }
}

@Composable
@Preview
fun PreviewMemo() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            LoginRoute(padding = PaddingValues(10.dp))
        }
    }
}
