package com.memozi.memo

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.memozi.component.textfield.MemoziSearchTextField
import com.memozi.component.top.MemoziBackGround
import com.memozi.component.top.MemoziTopAppbar
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.extension.customClickable
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    MemoziBackGround()
    Column {
        MemoziTopAppbar(
            innerComposable = {
                MemoziSearchTextField(
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        )
        MemoziViewPager(pagerState, modifier = Modifier.padding(top = 15.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MemoziHorizontalPagerIndicator(
                pagerState
            )
            Spacer(modifier = Modifier.height(16.dp))
            MemoList(memoItems = dummyMemoItems())
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { /* TODO: Navigate to add memo screen */ },
            shape = CircleShape,
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Transparent),
            containerColor = MemoziTheme.colors.mainPurple02,
            contentColor = MemoziTheme.colors.white,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Memo"
            )
        }
    }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziViewPager(
    pagerState: PagerState,
    modifier: Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 104.dp)
    ) { page ->
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        Box(
            modifier = Modifier
                .width(152.dp)
                .height(88.dp)
                .graphicsLayer {
                    lerp(
                        start = 0.78f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                    }
                    lerp(
                        start = 0.9f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleY = scale
                    }
                }
                .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)
        ) {
            MemoziCategory(
                painterResource(id = R.drawable.img_memozi_category_1),
                "카테고리",
                MemoziTheme.colors.white
            )
        }
    }
}

@Composable
fun MemoziCategory(
    painter: Painter,
    title: String,
    titleColor: Color
) {
    Box {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Text(
            text = title,
            style = MemoziTheme.typography.ssuLight13,
            color = titleColor,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp)
                .padding(horizontal = 9.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziHorizontalPagerIndicator(
    pagerState: PagerState,
    navigateToEdit: () -> Unit = {},
    pageCount: Int = pagerState.pageCount,
    modifier: Modifier = Modifier,
    activeColor: Color = MemoziTheme.colors.mainPurple,
    inactiveColor: Color = MemoziTheme.colors.gray05,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    indicatorNotSelectedWidth: Dp = 6.dp,
    indicatorNotSelectedHeight: Dp = indicatorNotSelectedWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: RoundedCornerShape = CircleShape
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { page ->
            val isSelected = page == pagerState.currentPage
            Box(
                modifier = Modifier
                    .size(
                        width = if (isSelected) indicatorWidth else indicatorNotSelectedWidth,
                        height = if (isSelected) indicatorHeight else indicatorNotSelectedHeight
                    )
                    .clip(indicatorShape)
                    .background(if (isSelected) activeColor else inactiveColor)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "edit",
            modifier = Modifier.customClickable(onClick = navigateToEdit)
        )
    }
}

data class MemoItem(val title: String, val content: String, val date: String)

fun dummyMemoItems(): List<MemoItem> {
    return listOf(
        MemoItem("Memo Title 1", "This is the content of memo 1", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 2", "This is the content of memo 2", "2024-08-31"),
        MemoItem("Memo Title 3", "This is the content of memo 3", "2024-08-31")
        // Add more items as needed
    )
}

@Composable
fun MemoList(memoItems: List<MemoItem>) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)) // elevation 효과를 위해 shadow 추가
            .background(color = MemoziTheme.colors.white, shape = RoundedCornerShape(8.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(memoItems.size) { index ->
                if (memoItems.size > 1 && index != memoItems.size - 1) {
                    MemoItemCard(memoItems[index])
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = 8.dp)
                            .shadow(1.dp, shape = RoundedCornerShape(2.dp))
                    )
                } else {
                    MemoItemCard(memoItems[index])
                }
            }
        }
    }
}

@Composable
fun MemoItemCard(memo: MemoItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = MemoziTheme.colors.white, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "|",
                style = MemoziTheme.typography.ngReg15,
                color = MemoziTheme.colors.gray07
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = memo.title,
                style = MemoziTheme.typography.ngReg15,
                color = MemoziTheme.colors.black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = memo.date,
                style = MemoziTheme.typography.ngReg11,
                color = MemoziTheme.colors.gray03
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = memo.content,
            style = MemoziTheme.typography.ssuLight12,
            color = MemoziTheme.colors.gray05
        )
    }
}

@Composable
@Preview
fun PreviewMemo() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            MemoRoute(padding = PaddingValues(10.dp))
        }
    }
}
