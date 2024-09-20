package com.memozi.memo.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.component.top.MemoziBackground
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.memo.MemoFloatingButton
import com.memozi.memo.model.SearchResult

@Composable
fun MemoSearchScreen(viewModel: MemoSearchViewModel = hiltViewModel()) {
    MemoziBackground(topWeight = 5f, bottomWeight = 25f)
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp + navigationBarHeight),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MemoTextField(onValueChange = { viewModel.getResult(it) })
        }

        if (state.result.isEmpty()) {
            EmptySearchList()
        } else {
            memoSearchList(state.result)
        }
    }
}

@Composable
fun EmptySearchList() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hing),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "항목을 찾을 수 없어요ㅠ",
                style = MemoziTheme.typography.ssuLight15,
                color = MemoziTheme.colors.black,
            )
        }
        MemoFloatingButton()
    }
}

@Composable
fun MemoItemCard(
    memoTitle: String,
    memoContent: String,
    memoDate: String,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(328f / 111f)
                .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(18.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "|",
                        style = MemoziTheme.typography.ngReg15,
                        color = MemoziTheme.colors.gray07,
                    )
                    Spacer(modifier = Modifier.width(4.dp))

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

            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = memoContent,
                style = MemoziTheme.typography.ngReg13,
                color = MemoziTheme.colors.black,
            )
        }
    }
}

@Composable
fun memoSearchList(searchResults: List<SearchResult>) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
    ) {
        searchResults.forEach { searchResult ->
            item {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = searchResult.name,
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.alignByBaseline(),
                    )
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = "카테고리에서 ${searchResult.count}개의 메모를 발견했습니다!",
                        style = MemoziTheme.typography.ngReg8,
                        modifier = Modifier.alignByBaseline(),
                    )
                }
            }

            items(searchResult.memos) { memo ->
                MemoItemCard(
                    memoTitle = memo.title,
                    memoContent = memo.content,
                    memoDate = memo.dayOfWeek,
                )
            }
        }
    }
}

@Composable
fun MemoTextField(onValueChange: (String) -> Unit = { _ -> }) {
    var text by remember { mutableStateOf("") }
    val maxCharCount = 10
    val shape = RoundedCornerShape(8.dp)

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(42.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .background(shape = shape, color = MemoziTheme.colors.white),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier =
                        Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                )
                BasicTextField(
                    value = text,
                    maxLines = 1,
                    onValueChange = {
                        if (it.length <= maxCharCount && !it.contains(" ")) {
                            text = it
                            onValueChange(text)
                        }
                    },
                    modifier =
                        Modifier
                            .padding(start = 10.dp)
                            .fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .background(shape = shape, color = MemoziTheme.colors.white),
                    textStyle = MemoziTheme.typography.ssuLight12.copy(MemoziTheme.colors.black),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = "메모를 검색해 보세요!",
                                style = MemoziTheme.typography.ssuLight12.copy(MemoziTheme.colors.gray05),
                            )
                        }
                        innerTextField()
                    },
                )
            }
            if (text.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_x_white_13), // 원하는 아이콘을 사용하세요.
                    contentDescription = null,
                    modifier =
                        Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .padding(end = 8.dp)
                            .clickable { text = "" },
                    tint = Color.Unspecified,
                )
            }
        }
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Text(
            text = "취소",
            style = MemoziTheme.typography.ssuLight13.copy(color = MemoziTheme.colors.white),
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewMemoSearchScreenWithEmptyView() {
    MemoziTheme {
        MemoSearchScreen()
    }
}
