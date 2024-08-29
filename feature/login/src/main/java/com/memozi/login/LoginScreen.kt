package com.memozi.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.Kakao
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun LoginRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LoginScreen()
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
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
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
            contentDescription = "로고에용~"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(256f)
        )
        Button(
            onClick = { /*TODO*/ },
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
