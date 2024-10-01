package com.memozi.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.extension.customClickable

@Composable
fun SettingMainScreen(
    navigateToSettingDelete: () -> Unit = {},
    navigateToSettingInfo: () -> Unit = {},
    viewmodel: SettingViewmodel = hiltViewModel()
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val state = viewmodel.uiState.collectAsStateWithLifecycle().value
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(color = MemoziTheme.colors.white)
    ) {
        Text(
            text = "설정",
            style = MemoziTheme.typography.ssuLight19,
            modifier =
            Modifier
                .padding(top = 32.dp + navigationBarHeight)
                .align(Alignment.CenterHorizontally)
        )

        PersonalInfoBox(onclickEvent = navigateToSettingInfo, email = state.email)
//
//        AlarmSettingsSection()
//
//        CustomerCenterSection()
    }
}

@Composable
fun PersonalInfoBox(
    isIconVisible: Boolean = true,
    onclickEvent: () -> Unit = {},
    email: String
) {
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .customClickable { onclickEvent() }
            .padding(top = 24.dp)
            .border(1.dp, color = MemoziTheme.colors.gray02, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(text = "내 가입정보", style = MemoziTheme.typography.ngBold14)
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = email,
                    style = MemoziTheme.typography.ngReg15
                )
                if (isIconVisible) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
                        contentDescription = null
                    )
                } else {
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier =
                    Modifier
                        .size(11.dp)
                        .background(
                            color = Color(0xFFFEE500),
                            shape = RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(8.dp),
                        painter = painterResource(id = R.drawable.ic_kakako),
                        contentDescription = null
                    )
                }
                Text(
                    text = "카카오 로그인 사용중",
                    style = MemoziTheme.typography.ngReg8,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}

@Composable
fun AlarmSettingsSection() {
    Text(
        text = "일기 알람",
        style = MemoziTheme.typography.ngBold14.copy(MemoziTheme.colors.gray07),
        modifier = Modifier.padding(top = 16.dp)
    )
    Box(
        modifier =
        Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray07)
    )
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "시간 수정", style = MemoziTheme.typography.ngReg15)
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
            contentDescription = null
        )
    }
    Box(
        modifier =
        Modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray02)
    )
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "시간 팝업 알림", style = MemoziTheme.typography.ngReg15)
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
            contentDescription = null
        )
    }
    Box(
        modifier =
        Modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray02)
    )
}

@Composable
fun CustomerCenterSection() {
    Text(
        text = "고객 센터",
        style = MemoziTheme.typography.ngBold14.copy(MemoziTheme.colors.gray07),
        modifier = Modifier.padding(top = 78.dp)
    )
    Box(
        modifier =
        Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray07)
    )
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "공지사항", style = MemoziTheme.typography.ngReg15)
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
            contentDescription = null
        )
    }
    Box(
        modifier =
        Modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray02)
    )
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "문제신고", style = MemoziTheme.typography.ngReg15)
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
            contentDescription = null
        )
    }
    Box(
        modifier =
        Modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(MemoziTheme.colors.gray02)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSetting() {
    MemoziTheme {
        SettingMainScreen()
    }
}
