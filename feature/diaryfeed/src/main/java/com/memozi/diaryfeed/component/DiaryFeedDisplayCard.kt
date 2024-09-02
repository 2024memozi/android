package com.memozi.diaryfeed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun DiaryFeedDisplayCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                shape = RoundedCornerShape(12.dp),
                color = MemoziTheme.colors.white
            )
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(MemoziTheme.colors.white),
        content = {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(brush = MemoziTheme.colors.gradientBrush)
                        ) {
                            append("Diary Of Nov.")
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = MemoziTheme.typography.appnameBold13
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(1.dp)
                        .background(MemoziTheme.colors.gray02)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(5) {
                        DailyDiaryItem(
                            year = 2024,
                            month = 11,
                            day = 8,
                            dayOfWeek = "금요일",
                            diaryContent = "일본이 너무 가고싶은 날이다. 얼른 종강이 왔으면 좋겠다.",
                            imageId = R.drawable.img_diary_feed_dummy_photo
                        )
                    }
                }

            }

        }
    )
}