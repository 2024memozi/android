package com.memozi.diaryfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.component.top.MemoziBackGround
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.diaryfeed.component.DiaryFeedDisplayCard
import com.memozi.diaryfeed.component.DiaryFeedGreeting
import com.memozi.diaryfeed.component.DiaryFeedTopAppBar
import com.memozi.diaryfeed.component.DiaryFeedWriteCard

@Composable
fun DiaryFeedScreen() {
    var listSelectedState by remember { mutableStateOf(true) }
    var calendarSelectedState by remember { mutableStateOf(false) }
    var isDiaryWritten by remember { mutableStateOf(false) }
    var isDiaryExist by remember { mutableStateOf(true) }

    MemoziBackGround()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        DiaryFeedTopAppBar()

        DiaryFeedGreeting()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter =
                if (listSelectedState) painterResource(id = R.drawable.ic_diary_feed_list_selected)
                else painterResource(id = R.drawable.ic_diary_feed_list_not_selected),
                contentDescription = null,
                modifier = Modifier.clickable {
                    calendarSelectedState = listSelectedState
                    listSelectedState = !listSelectedState
                }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_white),
                contentDescription = null
            )
            Image(
                painter =
                if (calendarSelectedState) painterResource(id = R.drawable.ic_diary_feed_calendar_selected)
                else painterResource(id = R.drawable.ic_diary_feed_calendar_not_selected),
                contentDescription = null,
                modifier = Modifier.clickable {
                    listSelectedState = calendarSelectedState
                    calendarSelectedState = !calendarSelectedState
                }
            )
        }

        if (!isDiaryWritten) {
            DiaryFeedWriteCard(
                onDiaryWrittenChange = { isDiaryWritten = it }
            )
        }

        if (isDiaryExist) {
            DiaryFeedDisplayCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDiaryFeedScreen() {
    MemoziTheme {
        DiaryFeedScreen()
    }
}