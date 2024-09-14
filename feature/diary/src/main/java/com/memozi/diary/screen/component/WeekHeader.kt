package com.memozi.diary.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import java.time.DayOfWeek

@Composable
fun WeekHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // DayOfWeek 객체를 직접 사용하여 요일을 나타냄
        val weekLabelArray = listOf(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
        )
        // 요일별로 순회하며 화면에 출력
        weekLabelArray.forEach { dayOfWeek ->
            // 일요일과 토요일의 색상을 구분하여 적용
            val textColor = when (dayOfWeek) {
                DayOfWeek.SUNDAY -> MemoziTheme.colors.cautionRed  // 일요일은 빨간색
                DayOfWeek.SATURDAY -> MemoziTheme.colors.blue02 // 토요일은 파란색
                else -> Color.Black  // 나머지는 검은색
            }

            Box(
                modifier = Modifier.width(((LocalConfiguration.current.screenWidthDp - 40) / 7).dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dayOfWeek.toString()[0].toString(), // 첫 글자만 출력 (S, M, T, W 등)
                    color = textColor
                )
            }
        }
    }
}