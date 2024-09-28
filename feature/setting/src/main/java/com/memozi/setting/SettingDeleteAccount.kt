package com.memozi.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.designsystem.MemoziTheme
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingDeleteAccount(
    navigateToLogin: () -> Unit = {},
    viewmodel: SettingViewmodel = hiltViewModel()
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    var isNotification by remember { mutableStateOf(false) }
    val buttonBackgroundColor =
        if (isNotification) MemoziTheme.colors.mainPurple else MemoziTheme.colors.gray02

    val state = viewmodel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffectWithLifecycle {
        viewmodel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SettingSideEffect.Delete -> {
                    navigateToLogin()
                }

                SettingSideEffect.Info -> TODO()
                SettingSideEffect.Logout -> TODO()
            }
        }
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = navigationBarHeight)
            .background(color = MemoziTheme.colors.white)
    ) {
        Text(
            text = "계정 탈퇴",
            style = MemoziTheme.typography.ssuLight19,
            modifier =
            Modifier
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "잠시만요, ${state.name}님!",
            style = MemoziTheme.typography.ngBold21,
            modifier = Modifier.padding(top = 52.dp)
        )
        Text(
            text = "- 계정 탈퇴 시, 모든 메모와 일기 데이터는 삭제됩니다.",
            style = MemoziTheme.typography.ngReg11,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "- 모든 데이터는 복구가 불가능합니다.",
            style = MemoziTheme.typography.ngReg11,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "- 이상으로 위의 사항에 동의하며 탈퇴 진행을 원할 경우,\n  아래 동의하기 버튼을 눌러 탈퇴를 진행해주세요.",
            style = MemoziTheme.typography.ngReg11,
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            RadioButton(
                selected = isNotification,
                onClick = { isNotification = !isNotification },
                colors =
                RadioButtonDefaults.colors(
                    selectedColor = MemoziTheme.colors.mainPurple,
                    unselectedColor = MemoziTheme.colors.mainPurple
                )
            )
            Text(
                text = "탈퇴에 동의합니다.",
                style = MemoziTheme.typography.ssuLight15
            )
        }

        Button(
            onClick = { viewmodel.navigateDelete() },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 18.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
            enabled = isNotification
        ) {
            Text(
                text = "확 인",
                style = MemoziTheme.typography.ngReg15
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeleteAccount() {
    MemoziTheme {
        SettingDeleteAccount()
    }
}
