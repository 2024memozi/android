package com.memozi.memo.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.component.textfield.MemoziSearchTextField
import com.memozi.component.top.MemoziBackground
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.memo.MemoFloatingButton
import com.memozi.memo.component.MemoItemCard
import com.memozi.memo.model.SearchResult
import com.memozi.ui.extension.customClickable

@Composable
fun MemoSearchScreen(
    viewModel: MemoSearchViewModel = hiltViewModel()
) {
    MemoziBackground(topWeight = 1f, bottomWeight = 5f)
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = navigationBarHeight)
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MemoziSearchTextField(onValueChange = { viewModel.getResult(it) })
            Spacer(modifier = Modifier.width(16.dp))
            if(state.query.isNotEmpty()) Icon(
                painter = painterResource(id = R.drawable.ic_x_white_13),
                tint = Color.Unspecified,
                contentDescription = "삭제버튼",
                modifier = Modifier.customClickable {
                    viewModel
                }
            )
        }
        Box(modifier = Modifier.weight(5f)){
            if (state.result.isEmpty()) {
                EmptySearchList()
            } else {
                memoSearchList(state.result)
            }
        }

    }
}

@Composable
fun EmptySearchList() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier =
            Modifier
                .align(Alignment.Center),
            // 중앙 정렬
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hing),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "항목을 찾을 수 없어요ㅠ",
                style = MemoziTheme.typography.ssuLight15,
                color = MemoziTheme.colors.black
            )
        }
        MemoFloatingButton()
    }
}

@Composable
fun memoSearchList(searchResults: List<SearchResult>) {
    LazyColumn(
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        searchResults.forEach { searchResult ->
            item {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = searchResult.name,
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.alignByBaseline()
                    )
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = "카테고리에서 ${searchResult.count}개의 메모를 발견했습니다!",
                        style = MemoziTheme.typography.ngReg8,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }

            items(searchResult.memos) { memo ->
                MemoItemCard(
                    memo = memo,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMemoSearchScreenWithEmptyView() {
    MemoziTheme {
        MemoSearchScreen()
    }
}
