package com.memozi.diaryfeed.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme

@Composable
fun DiaryFeedGreeting() {
    var userName by remember { mutableStateOf("홍길동") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = userName,
            color = MemoziTheme.colors.white,
            style = MemoziTheme.typography.ngBold15
        )
        Text(
            text = " 님, 좋은 저녁이에요! 오늘 하루는 어떠셨나요?",
            color = MemoziTheme.colors.white,
            style = MemoziTheme.typography.ngReg13
        )
    }
}