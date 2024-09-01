package com.memozi.diaryfeed.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun DiaryFeedWriteCard(
    onDiaryWrittenChange: (Boolean) -> Unit
) {
    var diaryWriteState by remember { mutableStateOf(false) }
    var diaryContent by remember { mutableStateOf("") }
    val isDiaryAvailable by remember(diaryContent) {
        mutableStateOf(diaryContent.isNotEmpty())
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
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
            if (!diaryWriteState) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_feed_plus),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable { diaryWriteState = true }
                    )
                    Text(
                        text = "오늘은 일기를 적지 않으셨어요 !\n 오늘의 기억을 적어주세요 :)",
                        style = MemoziTheme.typography.ssuLight13
                    )
                }
            }
            if (diaryWriteState) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    BasicTextField(
                        value = diaryContent,
                        onValueChange = { newContent ->
                            diaryContent = newContent
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                        textStyle = MemoziTheme.typography.ngReg12_140.copy(color = Color.Black) // 텍스트 색상 설정
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_diary_feed_camera),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Bottom)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_diary_feed_gallery),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Bottom)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_diary_feed_pin),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Bottom)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_diary_feed_random),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Bottom)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(brush = MemoziTheme.colors.gradientBrush)
                                ) {
                                    append("100")
                                }
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .align(Alignment.Bottom)
                        )
                        Button(
                            onClick = { onDiaryWrittenChange(true) /* 일기 작성 완료 */ },
                            modifier = Modifier
                                .width(68.dp)
                                .height(34.dp)
                                .align(Alignment.CenterVertically),
                            enabled = isDiaryAvailable,
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonColors(
                                contentColor = MemoziTheme.colors.white,
                                containerColor = MemoziTheme.colors.mainPurple,
                                disabledContentColor = MemoziTheme.colors.white,
                                disabledContainerColor = MemoziTheme.colors.gray02
                            ),
                            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "등록",
                                modifier = Modifier.padding(0.dp),
                                style = MemoziTheme.typography.ssuLight12
                            )
                        }
                    }
                }
            }
        }
    )
}