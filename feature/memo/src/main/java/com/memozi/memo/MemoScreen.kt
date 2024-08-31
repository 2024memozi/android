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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.memozi.component.textfield.MemoziSearchTextField
import com.memozi.component.top.MemoziBackGround
import com.memozi.component.top.MemoziTopAppbar
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.extension.customClickable
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: MemoViewModel = hiltViewModel(),
    navigateMemoDetail: (Int) -> Unit = {},
    navigateToCategory: (Int) -> Unit = {},
    navigateSetting: () -> Unit = {}
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { dummyMemoCategoriesItems().size + 1 })
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    LaunchedEffectWithLifecycle() {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is MemoSideEffect.NavigateToMemo -> {
                    navigateMemoDetail(sideEffect.memoId)
                }

                is MemoSideEffect.NavigateToCategory -> {
                    navigateToCategory(sideEffect.categoryId)
                }

                MemoSideEffect.NavigateToSettings -> {
                    navigateSetting()
                }
            }
        }
    }

    MemoziBackGround()
    Column {
        MemoziTopAppbar(
            navigateToSetting = navigateSetting,
            innerComposable = {
                MemoziSearchTextField(
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        )
        MemoziHorizontalPager(
            pagerState,
            category = dummyMemoCategoriesItems(),
            modifier = Modifier.padding(top = 15.dp)
        )

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
            MemoList(
                memoItems = dummyMemoItems(),
                bottomPaddingValue = PaddingValues(bottom = 8.dp + navigationBarHeight)
            )
        }
    }
    MemoFloatingButton()
}

@Composable
fun MemoFloatingButton(
    navigateMemoAdd: () -> Unit = {}
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 51.dp + navigationBarHeight)
                .padding(end = 8.dp)
                .background(Color.Transparent)
                .width(55.dp)
                .height(55.dp)
                .customClickable(rippleEnabled = false) { navigateMemoAdd() } // 나중에 커스텀 clickable 추가
                .background(
                    color = MemoziTheme.colors.mainPurple02,
                    shape = CircleShape
                )
        ) {
            Icon(
                painterResource(id = R.drawable.ic_plus_white_34),
                contentDescription = "Add Memo",
                tint = MemoziTheme.colors.white,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziHorizontalPager(
    pagerState: PagerState,
    modifier: Modifier,
    category: List<CategoryItem>
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
            if (page == category.size) {
                MemoziCategoryAdd()
            } else {
                MemoziCategory(
                    imageURL = category[page].imageUrl,
                    title = category[page].name,
                    titleColor = Color(android.graphics.Color.parseColor(category[page].textColor))
                )
            }
        }
    }
}

@Composable
fun MemoziCategory(
    imageURL: String,
    title: String,
    titleColor: Color
) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
//            placeholder = painterResource(R.drawable.ic), 로딩화면 필요시 변경
            contentDescription = "카테고리",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = title,
            style = MemoziTheme.typography.ssuLight11,
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

@Composable
fun MemoziCategoryAdd() {
    Box(
        modifier = Modifier.customClickable(onClick = {}),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.img_category_add),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Text(
            text = "새 카테고리 추가",
            style = MemoziTheme.typography.ssuLight11,
            color = MemoziTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_plus_white_34),
            tint = MemoziTheme.colors.white,
            contentDescription = "카테고리추가"
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

@Composable
fun MemoList(memoItems: List<MemoItem>, bottomPaddingValue: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottomPaddingValue)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)) // elevation 효과를 위해 shadow 추가
            .background(color = MemoziTheme.colors.white, shape = RoundedCornerShape(8.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
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
    )
}

data class CategoryItem(
    val imageUrl: String,
    val name: String,
    val textColor: String
)

fun dummyMemoCategoriesItems(): List<CategoryItem> {
    return listOf(
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/2473834b-2be4-4584-b143-7e7269a6607c",
            name = "카테고리 1",
            textColor = "#FFFFFF"
        ),
        CategoryItem(
            imageUrl = "https://github.com/user-attachments/assets/6cf10fec-27cb-4367-ba97-40c937fbb92c",
            name = "카테고리 2",
            textColor = "#FF0000"
        )
    )
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
