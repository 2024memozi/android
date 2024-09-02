package com.memozi.diaryfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.component.top.MemoziBackground
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun DiaryFeedScreen() {
    var listSelectedState by remember { mutableStateOf(true) }
    var calendarSelectedState by remember { mutableStateOf(false) }
    var diaryWriteState by remember { mutableStateOf(false) }
    val maxDiaryLength = 100
    var diaryContent by remember { mutableStateOf("") }
    val isDiaryAvailable by remember(diaryContent) { mutableStateOf(diaryContent.isNotEmpty()) }
    var userName by remember { mutableStateOf("홍길동") }
    var isDiaryWritten by remember { mutableStateOf(false) }
    var isDiaryExist by remember { mutableStateOf(true) }

    MemoziBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        DiaryFeedTopAppBar()

        DiaryFeedGreeting(userName = userName)

        SelectDisplayType(
            listSelectedState = listSelectedState,
            onChangedListSelectedState = { listSelectedState = it },
            calendarSelectedState = calendarSelectedState,
            onChangedCalendarSelectedState = { calendarSelectedState = it }
        )

        if (!isDiaryWritten) {
            DiaryFeedWriteCard(
                diaryWriteState = diaryWriteState,
                onChangeDiaryWriteState = { diaryWriteState = it },
                maxDiaryLength = maxDiaryLength,
                diaryContent = diaryContent,
                onChangedDiaryContent = { diaryContent = it },
                isDiaryAvailable = isDiaryAvailable,
                onChangedDiaryWritten = { isDiaryWritten = it }
            )
        }

        if (isDiaryExist) {
            DiaryFeedDisplayCard()
        }
    }
}

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

@Composable
fun DiaryFeedGreeting(
    userName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MemoziTheme.colors.white,
                        fontSize = MemoziTheme.typography.ngBold15.fontSize,
                        fontWeight = MemoziTheme.typography.ngBold15.fontWeight
                    )
                ) {
                    append(userName)
                }
                withStyle(
                    style = SpanStyle(
                        color = MemoziTheme.colors.white,
                        fontSize = MemoziTheme.typography.ngReg13.fontSize,
                        fontWeight = MemoziTheme.typography.ngReg13.fontWeight
                    )
                ) {
                    append(" 님, 좋은 저녁이에요! 오늘 하루는 어떠셨나요?")
                }
            }
        )

    }
}

@Composable
fun SelectDisplayType(
    listSelectedState: Boolean,
    onChangedListSelectedState: (Boolean) -> Unit,
    calendarSelectedState: Boolean,
    onChangedCalendarSelectedState: (Boolean) -> Unit
) {
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
                onChangedListSelectedState(true)
                onChangedCalendarSelectedState(false)
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
                onChangedListSelectedState(false)
                onChangedCalendarSelectedState(true)
            }
        )
    }
}

@Composable
fun DiaryFeedWriteCard(
    diaryWriteState: Boolean,
    onChangeDiaryWriteState: (Boolean) -> Unit,
    maxDiaryLength: Int,
    diaryContent: String,
    onChangedDiaryContent: (String) -> Unit,
    isDiaryAvailable: Boolean,
    onChangedDiaryWritten: (Boolean) -> Unit
) {
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
                            .clickable { onChangeDiaryWriteState(true) }
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
                            if (newContent.length <= 100) {
                                onChangedDiaryContent(newContent)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                        textStyle = MemoziTheme.typography.ngReg12_140.copy(color = Color.Black)
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        DiaryFeedWriteOption(id = R.drawable.ic_diary_feed_camera)
                        DiaryFeedWriteOption(id = R.drawable.ic_diary_feed_gallery)
                        DiaryFeedWriteOption(id = R.drawable.ic_diary_feed_pin)
                        DiaryFeedWriteOption(id = R.drawable.ic_diary_feed_random)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(brush = MemoziTheme.colors.gradientBrush)
                                ) {
                                    append("${maxDiaryLength - diaryContent.length}")
                                }
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .align(Alignment.Bottom)
                        )
                        Button(
                            onClick = { onChangedDiaryWritten(true) /* 일기 작성 완료 */ },
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

@Composable
fun DiaryFeedWriteOption(
    id: Int,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier.clickable { onClick() }
    )
}

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

@Preview(showBackground = true)
@Composable
fun ShowDiaryFeedScreen() {
    MemoziTheme {
        DiaryFeedScreen()
    }
}