package com.memozi.memo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
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

@Composable
fun MemoItemCard(
    memoTitle: String,
    memoContent: String,
    memoDate: String,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_black),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = memoTitle,
                        style = MemoziTheme.typography.ngReg15,
                        color = MemoziTheme.colors.black,
                    )
                }
                Text(
                    text = memoDate,
                    style = MemoziTheme.typography.ngReg11,
                    color = MemoziTheme.colors.black,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = memoContent,
                style = MemoziTheme.typography.ngReg13,
                color = MemoziTheme.colors.black,
            )
        }
    }
}

@Composable
fun memoSearchList(dummyData: List<Pair<String, List<MemoItem>>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        dummyData.forEach { (title, memoItems) ->
            item {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = title,
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.alignByBaseline(),
                    )
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = "카테고리에서 ${memoItems.size}개의 메모를 발견했습니다!",
                        style = MemoziTheme.typography.ngReg8,
                        modifier = Modifier.alignByBaseline(),
                    )
                }
            }

            items(memoItems) { memo ->
                MemoItemCard(
                    memoTitle = memo.title,
                    memoContent = memo.content,
                    memoDate = memo.date,
                )
            }
        }
    }
}

@Composable
fun MemoSearchScreen() {
    MemoziBackground(topWeight = 10f, bottomWeight = 48f)
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp + navigationBarHeight),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MemoziSearchTextField()
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "취소",
                style = MemoziTheme.typography.ssuLight13,
                modifier =
                    Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            // 취소 버튼 클릭 시 동작
                        },
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val dummyData =
            listOf(
                "투두 리스트" to dummyMemoItems(),
                "구매 리스트" to dummyMemoItems().subList(0, 3),
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
