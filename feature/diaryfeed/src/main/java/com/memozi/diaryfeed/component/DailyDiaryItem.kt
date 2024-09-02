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
fun DailyDiaryItem(
    year: Int,
    month: Int,
    day: Int,
    dayOfWeek: String,
    diaryContent: String,
    imageId: Int? = null,
    location: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "{$year}년 {$month}월 {$day}일 | {$dayOfWeek}",
            modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
            style = MemoziTheme.typography.ssuLight12
        )
        if (imageId != null) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        if (location != null) {
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
                    text = location,
                    style = MemoziTheme.typography.ngReg8
                )
            }
        }
        Text(
            text = diaryContent,
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