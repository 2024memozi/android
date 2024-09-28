package com.memozi.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateHome: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is OnboardingSideEffect.NavigateToHome -> {
                    navigateHome()
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MemoziTheme.colors.white)
    ) { page ->
        when (page) {
            0 -> OnboardingOne(state.name)
            1 -> OnboardingTwo()
            2 -> OnboardingThree(navigateHome = { viewModel.navigateHome() })
            3 -> OnboardingFour(
                selectedAmPm = state.selectedAmPm,
                selectedHour = state.selectedHour,
                selectedMinute = state.selectedMinute,
                isNotification = state.isNotification,
                setAmPm = { viewModel.setAmPm(it) },
                setHour = { viewModel.setHour(it) },
                setMinute = { viewModel.setMinute(it) },
                setIsNotification = { viewModel.setIsNotification(it) }
            ) { viewModel.navigateHome() }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(619f))
        MemoziHorizontalPagerIndicator(pagerState = pagerState)

        Spacer(modifier = Modifier.weight(58f))
    }
}

@Composable
fun OnboardingOne(name: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(223f)
        )
        Column(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = "MEMO : Zi",
                style = MemoziTheme.typography.appnameBold13.copy(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MemoziTheme.colors.mainPink,
                            MemoziTheme.colors.mainPurple
                        )
                    )
                ),
                color = MemoziTheme.colors.mainPink
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "환영해요, ${name}님!\n회원가입이 완료 되었어요:)",
                style = MemoziTheme.typography.ssuLight16,
                color = MemoziTheme.colors.black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(347f)
        )
    }
}

@Composable
fun OnboardingTwo() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(80f)
        )
        Column(modifier = Modifier.padding(start = 24.dp, end = 44.dp)) {
            Text(
                text = "MEMO : Zi",
                style = MemoziTheme.typography.appnameBold13.copy(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MemoziTheme.colors.mainPink,
                            MemoziTheme.colors.mainPurple
                        )
                    )
                ),
                color = MemoziTheme.colors.mainPink
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "갑자기 떠오르는 영감이나, 꼭 해야할 일을 메모해 보세요:)",
                style = MemoziTheme.typography.ssuLight16,
                color = MemoziTheme.colors.black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(88f)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_onboarding_two_1),
            contentDescription = "예시 이미지 1"
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_onboarding_two_2),
            contentDescription = "예시 이미지 2"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(216f)
        )
    }
}

@Composable
fun OnboardingThree(navigateHome: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 44.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(80f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(67f)
            ) {
                Text(
                    text = "MEMO : Zi",
                    style = MemoziTheme.typography.appnameBold13.copy(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MemoziTheme.colors.mainPink,
                                MemoziTheme.colors.mainPurple
                            )
                        )
                    ),
                    color = MemoziTheme.colors.mainPink
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "갑자기 떠오르는 영감이나, 꼭 해야할 일을 메모해 보세요:)",
                    style = MemoziTheme.typography.ssuLight16,
                    color = MemoziTheme.colors.black
                )
            }
            Button(
                onClick = { navigateHome() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MemoziTheme.colors.mainPurple),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "MEMO : Zi 시작하기", color = MemoziTheme.colors.white)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(543f)
            )
        }
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_three),
                contentDescription = "이미지다옹~"
            )
        }
    }
}

@Composable
fun OnboardingFour(
    selectedAmPm: String = "",
    selectedHour: Int = 12,
    selectedMinute: Int = 12,
    isNotification: Boolean = false,
    setAmPm: (String) -> Unit = {},
    setHour: (Int) -> Unit = {},
    setMinute: (Int) -> Unit = {},
    setIsNotification: (Boolean) -> Unit = {},
    navigateHome: () -> Unit = {}
) {
    val amPmList = listOf("오전", "오후")
    val hourList = (1..12).toList()
    val minuteList = (0..59).toList()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(80f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(67f)
                .padding(start = 8.dp, end = 28.dp)
        ) {
            Text(
                text = "MEMO : Zi",
                style = MemoziTheme.typography.appnameBold13.copy(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MemoziTheme.colors.mainPink,
                            MemoziTheme.colors.mainPurple
                        )
                    )
                ),
                color = MemoziTheme.colors.mainPink
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "일기를 쓸 시간을 설정해 주세요.\n팝업 알림으로 알려드릴 수도 있어요:)",
                style = MemoziTheme.typography.ssuLight16,
                color = MemoziTheme.colors.black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(67f)
        )

        Box(
            modifier = if (isNotification) {
                Modifier
                    .background(
                        color = MemoziTheme.colors.black20,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.17f)
            } else {
                Modifier
                    .background(
                        color = MemoziTheme.colors.white,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.17f)
                    .shadow(3.dp, shape = RoundedCornerShape(8.dp))
            }
        ) {
            Row(
                modifier = Modifier
                    .background(color = if (isNotification) MemoziTheme.colors.black20 else MemoziTheme.colors.white)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberPicker(
                    items = amPmList,
                    selectedItem = selectedAmPm,
                    onItemSelected = { setAmPm(it) },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.Transparent),
                    enabled = !isNotification
                )

                NumberPicker(
                    items = hourList.map { it.toString().padStart(2, '0') },
                    selectedItem = selectedHour.toString().padStart(2, '0'),
                    onItemSelected = { setHour(it.toInt()) },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.Transparent),
                    enabled = !isNotification
                )

                NumberPicker(
                    items = minuteList.map { it.toString().padStart(2, '0') },
                    selectedItem = selectedMinute.toString().padStart(2, '0'),
                    onItemSelected = { setMinute(it.toInt()) },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.Transparent),
                    enabled = !isNotification
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(51f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(32f)
                .clickable(onClick = { setIsNotification(!isNotification) })
        ) {
            RadioButton(
                selected = isNotification,
                onClick = { setIsNotification(!isNotification) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MemoziTheme.colors.mainPurple,
                    unselectedColor = MemoziTheme.colors.mainPurple
                )
            )

            Text(
                text = "팝업 알림은 안받을래요.",
                style = MemoziTheme.typography.ssuLight15,
                color = MemoziTheme.colors.black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(88f)
        )
        Button(
            onClick = { navigateHome() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MemoziTheme.colors.mainPurple),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "MEMO : Zi 시작하기", color = MemoziTheme.colors.white)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(127f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumberPicker(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val selectedIndex = items.indexOf(selectedItem)

    Box(modifier = modifier.fillMaxHeight()) {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            userScrollEnabled = enabled
        ) {
            items(items) { item ->
                val isSelected = item == selectedItem
                Text(
                    style = if (isSelected) MemoziTheme.typography.ngBold21 else MemoziTheme.typography.ngReg14,
                    text = item,
                    color = if (isSelected) MemoziTheme.colors.black else MemoziTheme.colors.gray03,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .width(48.dp)
                .align(Alignment.Center)
                .offset(y = (-16).dp),
            thickness = 2.dp,
            color = MemoziTheme.colors.mainPurple
        )

        HorizontalDivider(
            modifier = Modifier
                .width(48.dp)
                .align(Alignment.Center)
                .offset(y = 16.dp),
            thickness = 2.dp,
            color = MemoziTheme.colors.mainPurple
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoziHorizontalPagerIndicator(
    pagerState: PagerState,
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
    }
}

@Composable
@Preview
fun PreviewOnboarding() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingRoute()
        }
    }
}

@Composable
@Preview
fun PreviewOnboardingFour() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingThree()
        }
    }
}
