package com.memozi.diaryfeed.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun DailyDiaryItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "2023년 11월 10일 | 금요일",
            modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
            style = MemoziTheme.typography.ssuLight12
        )
        Image(
            painter = painterResource(id = R.drawable.img_diary_feed_dummy_photo),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_diary_feed_location),
                contentDescription = null,
                modifier = Modifier.padding(end = 2.dp)
            )
            Text(
                text = "일본 도쿄",
                style = MemoziTheme.typography.ngReg8
            )
        }
        Text(
            text = "일본이 너무 가고싶은 날이다. 얼른 종강이 왔으면 좋겠다.",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MemoziTheme.typography.ngReg12_140
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MemoziTheme.colors.gray02)
        )
    }
}