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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.memozi.component.textfield.MemoziSearchTextField
import com.memozi.component.top.MemoziBackground
import com.memozi.component.top.MemoziTopAppbar
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.memo.model.Category
import com.memozi.memo.model.Memo
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
    navigateDiary: () -> Unit,
    navigateMemoDetail: (Int, Int) -> Unit,
    navigateMemoAdd: (Int) -> Unit = {},
    navigateToCategoryEdit: (String, Int, String, String) -> Unit,
    navigateToCategoryAdd: () -> Unit = {},
    navigateSetting: () -> Unit = {},
    navigateSearch: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { state.categoryList.size + 1 })
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    LaunchedEffectWithLifecycle {
        viewModel.getCategory()
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is MemoSideEffect.NavigateToMemo -> {
                    navigateMemoDetail(
                        state.categoryList[pagerState.currentPage].categoryId,
                        sideEffect.memoId
                    )
                }

                is MemoSideEffect.NavigateToCategory -> {
                    navigateToCategoryEdit(
                        state.categoryList[pagerState.currentPage].representImage,
                        state.categoryList[pagerState.currentPage].categoryId,
                        state.categoryList[pagerState.currentPage].name,
                        state.categoryList[pagerState.currentPage].txtColor
                    )
                }

                is MemoSideEffect.NavigateToCategoryAdd -> {
                    navigateToCategoryAdd()
                }

                MemoSideEffect.NavigateToSettings -> {
                    navigateSetting()
                }

                MemoSideEffect.NavigateSearch -> {
                    navigateSearch()
                }

                is MemoSideEffect.NavigateMemoAdd -> {
                    navigateMemoAdd(state.categoryList[pagerState.currentPage].categoryId)
                }
            }
        }
    }
    if (pagerState.currentPage < state.categoryList.size) {
        viewModel.getCategory(state.categoryList[pagerState.currentPage].categoryId)
    } else {
        viewModel.setMemoEmpty()
    }
    MemoziBackground()
    Column {
        MemoziTopAppbar(
            navigateToSetting = navigateSetting,
            navigateToFirst = navigateDiary,
            innerComposable = {
                MemoziSearchTextField(
                    modifier = Modifier
                        .height(40.dp)
                        .customClickable(rippleEnabled = false) { viewModel.navigateSearch() }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enable = false
                )
            }
        )
        MemoziHorizontalPager(
            pagerState,
            category = state.categoryList,
            modifier = Modifier.padding(top = 15.dp),
            navigateToCategoryAdd = { viewModel.navigateCategoryAdd() }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MemoziHorizontalPagerIndicator(
                pagerState,
                editClickEvent = { viewModel.navigateCategory(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            MemoList(
                categoryId = if (pagerState.currentPage < state.categoryList.size) state.categoryList[pagerState.currentPage].categoryId else 0,
                memoItems = state.memoList,
                bottomPaddingValue = PaddingValues(bottom = 8.dp + navigationBarHeight),
                clickEvnet = { categoryId, memoId ->
                    navigateMemoDetail(categoryId, memoId)
                }
            )
        }
    }
    MemoFloatingButton(
        navigateMemoAdd = { viewModel.navigateMemoAdd() },
        visibility = pagerState.currentPage != pagerState.pageCount - 1
    )
}

@Composable
fun MemoFloatingButton(
    navigateMemoAdd: () -> Unit = {},
    visibility: Boolean = true
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    if (visibility) {
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
                    .customClickable(rippleEnabled = false) { navigateMemoAdd() }
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
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziHorizontalPager(
    pagerState: PagerState,
    modifier: Modifier,
    category: List<Category>,
    navigateToCategoryAdd: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val padding = 0.288f * screenWidth // 104/360

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = padding)
    ) { page ->
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val itemWidth =
            if (page == pagerState.currentPage) 0.422f * screenWidth else 0.35f * screenWidth
        val itemHeight =
            if (page == pagerState.currentPage) 0.244f * screenWidth else 0.2f * screenWidth

        Box(
            modifier = Modifier
                .width(itemWidth)
                .height(itemHeight)
                .graphicsLayer {
                    lerp(
                        start = 0.78f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
                .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)
        ) {
            if (page == category.size) {
                MemoziCategoryAdd(navigateToCategoryAdd = navigateToCategoryAdd)
            } else {
                MemoziCategory(
                    imageURL = category[page].representImage,
                    title = category[page].name,
                    titleColor = Color(android.graphics.Color.parseColor(category[page].txtColor))
                )
            }
        }
    }
}

@Composable
fun MemoziCategory(
    modifier: Modifier = Modifier,
    imageURL: String,
    title: String,
    titleColor: Color,
    textStyle: TextStyle = MemoziTheme.typography.ssuLight11,
    textModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        .padding(horizontal = 9.dp)
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
//            placeholder = painterResource(R.drawable.ic), 로딩화면 필요시 변경
            contentDescription = "카테고리",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(14.dp))
        )
        Text(
            text = title,
            style = textStyle,
            color = titleColor,
            textAlign = TextAlign.End,
            modifier = textModifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun MemoziCategoryAdd(navigateToCategoryAdd: () -> Unit) {
    Box(
        modifier = Modifier.customClickable(onClick = { navigateToCategoryAdd() }),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.img_category_add),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_white_34),
                tint = MemoziTheme.colors.white,
                contentDescription = "카테고리추가"
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "새 카테고리 추가",
                style = MemoziTheme.typography.ssuLight11,
                color = MemoziTheme.colors.white,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziHorizontalPagerIndicator(
    pagerState: PagerState,
    editClickEvent: (Int) -> Unit = {},
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
        if (pagerState.currentPage != pagerState.pageCount - 1) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit",
                modifier = Modifier.customClickable(onClick = { editClickEvent(pagerState.currentPage) })
            )
        }
    }
}

@Composable
fun MemoList(
    categoryId: Int,
    memoItems: List<Memo>,
    bottomPaddingValue: PaddingValues,
    clickEvnet: (Int, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottomPaddingValue)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)) // elevation 효과를 위해 shadow 추가
            .background(color = MemoziTheme.colors.white, shape = RoundedCornerShape(8.dp))

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(memoItems.size) { index ->
                if (memoItems.size > 1 && index != memoItems.size - 1) {
                    MemoItemCard(
                        Modifier.customClickable { clickEvnet(categoryId, memoItems[index].memoId) },
                        memoItems[index]
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = 8.dp)
                            .shadow(1.dp, shape = RoundedCornerShape(2.dp))
                    )
                } else {
                    MemoItemCard(
                        Modifier.customClickable { clickEvnet(categoryId, memoItems[index].memoId) },
                        memoItems[index]
                    )
                }
            }
        }
    }
}

@Composable
fun MemoItemCard(modifier: Modifier, memo: Memo) {
    Column(
        modifier = modifier
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
                text = memo.dayOfWeek,
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
//            MemoRoute(padding = PaddingValues(10.dp))
        }
    }
}
