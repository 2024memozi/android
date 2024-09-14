package com.memozi.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.extension.customClickable

@Composable
fun MemoFloatingButton(navigateMemoAdd: () -> Unit = {}) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Box(
        modifier =
            Modifier
                .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Box(
            modifier =
                Modifier
                    .padding(bottom = 51.dp + navigationBarHeight)
                    .padding(end = 8.dp)
                    .background(Color.Transparent)
                    .width(55.dp)
                    .height(55.dp)
                    .customClickable(rippleEnabled = false) { navigateMemoAdd() } // 나중에 커스텀 clickable 추가
                    .background(
                        color = MemoziTheme.colors.mainPurple02,
                        shape = CircleShape,
                    ),
        ) {
            Icon(
                painterResource(id = R.drawable.ic_plus_white_34),
                contentDescription = "Add Memo",
                tint = MemoziTheme.colors.white,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
