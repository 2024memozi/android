package com.memozi.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memozi.designsystem.MemoziTheme
import com.memozi.ui.extension.customClickable
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingMyInfo(
    viewmodel: SettingViewmodel = hiltViewModel(),
    navigateDelete: () -> Unit = {},
    navigateLogin: () -> Unit = {}
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    LaunchedEffectWithLifecycle {
        viewmodel.sideEffect.collectLatest {
            when (it) {
                SettingSideEffect.Delete -> navigateDelete()
                SettingSideEffect.Info -> TODO()
                SettingSideEffect.Logout -> navigateLogin()
            }
        }
    }
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(color = MemoziTheme.colors.white)
    ) {
        Text(
            text = "내 가입정보",
            style = MemoziTheme.typography.ssuLight19,
            modifier =
            Modifier
                .padding(top = 32.dp + navigationBarHeight)
                .align(Alignment.CenterHorizontally)
        )
        PersonalInfoBox(isIconVisible = false)
        Text(
            text = "로그아웃",
            style = MemoziTheme.typography.ngReg15.copy(color = Color.Red),
            modifier = Modifier
                .padding(top = 16.dp)
                .customClickable {
                    viewmodel.logOut()
                }
        )
        Text(
            text = "회원 탈퇴",
            style = MemoziTheme.typography.ngReg15,
            modifier = Modifier
                .padding(top = 22.dp)
                .customClickable {
                    viewmodel.navigateDelete()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyInfo() {
    MemoziTheme {
        SettingMyInfo()
    }
}
