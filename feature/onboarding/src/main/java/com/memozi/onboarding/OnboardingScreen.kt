package com.memozi.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.ui.extension.customClickable
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateHome: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
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
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> OnboardingOne()
            1 -> OnboardingTwo()
            2 -> OnboardingThree()
            3 -> OnboardingFour { viewModel.navigateHome() }
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
fun OnboardingOne() {
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
                text = "환영해요, 차혜빈님!\n회원가입이 완료 되었어요:)",
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
fun OnboardingThree() {
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
    navigateHome: () -> Unit = {}
) {
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
        // TODO Date Time Picker 공간
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(67f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(50f)
        )
        // TODO 팝업 알림 라디오 버튼 공간
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(32f)
        )
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
fun PreviewOnboardingOne() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingOne()
        }
    }
}

@Composable
@Preview
fun PreviewOnboardingTwo() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingTwo()
        }
    }
}

@Composable
@Preview
fun PreviewOnboardingThree() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingThree()
        }
    }
}

@Composable
@Preview
fun PreviewOnboardingFour() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingFour()
        }
    }
}
