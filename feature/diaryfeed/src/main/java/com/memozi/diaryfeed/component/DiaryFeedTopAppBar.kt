package com.memozi.diaryfeed.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
fun DiaryFeedTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "MEMO : Zi",
            style = MemoziTheme.typography.appnameBold15
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "메모",
            modifier = Modifier.clickable { /* TODO : 메모 페이지로 이동 */ },
            style = MemoziTheme.typography.ssuLight13
        )
        Image(
            painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_black),
            contentDescription = null
        )
        Text(
            text = "설정",
            modifier = Modifier.clickable { /* TODO : 설정 페이지로 이동 */ },
            style = MemoziTheme.typography.ssuLight13
        )
    }
}