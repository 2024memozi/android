package com.memozi.memo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.component.textfield.MemoziSearchTextField
import com.memozi.component.top.MemoziBackground
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.memo.model.MemoItem
import com.memozi.memo.model.dummyMemoItems

// MemoItemCard에서 메모 제목, 내용, 날짜를 받아서 처리
@Composable
fun MemoItemCard(
    memoTitle: String,
    memoContent: String,
    memoDate: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp), // 모서리 radius
        elevation = CardDefaults.cardElevation(4.dp), // elevation을 줘서 그림자 효과 적용
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // 흰색 배경
                .padding(8.dp)
        ) {
            // 첫 번째 Row: 제목과 날짜
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 날짜를 양쪽에 배치
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_black),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // 아이콘과 텍스트 사이에 간격 추가
                    Text(
                        text = memoTitle,
                        style = MemoziTheme.typography.ngReg15,
                        color = MemoziTheme.colors.black
                    )
                }
                Text(
                    text = memoDate, // 서버에서 받아온 날짜 사용
                    style = MemoziTheme.typography.ngReg11,
                    color = MemoziTheme.colors.black // 적절한 색상 적용
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 두 번째: 메모 내용
            Text(
                text = memoContent,
                style = MemoziTheme.typography.ngReg13,
                color = MemoziTheme.colors.black
            )
        }
    }
}

// memoSearchList에서 메모 제목, 내용, 날짜를 받아서 처리
@Composable
fun memoSearchList(dummyData: List<Pair<String, List<MemoItem>>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        dummyData.forEach { (title, memoItems) ->
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.alignByBaseline()
                    )
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = "카테고리에서 ${memoItems.size}개의 메모를 발견했습니다!",
                        style = MemoziTheme.typography.ngReg8,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }

            items(memoItems) { memo ->
                MemoItemCard(
                    memoTitle = memo.title,
                    memoContent = memo.content,
                    memoDate = memo.date
                )
            }
        }
    }
}

// MemoSearchScreen에서 dummyData에 메모 제목과 날짜 추가
@Composable
fun MemoSearchScreen() {
    MemoziBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MemoziSearchTextField()
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "취소",
                style = MemoziTheme.typography.ssuLight13,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        // 취소 버튼 클릭 시 동작
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dummy data using MemoItem class
        val dummyData = listOf(
            "투두 리스트" to dummyMemoItems(),
            "구매 리스트" to dummyMemoItems().subList(0, 3)
        )

        memoSearchList(dummyData = dummyData)
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMemoSearchScreen() {
    MemoziTheme {
        MemoSearchScreen()
    }
}
