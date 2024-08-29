package com.memozi.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun OnboardingRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    OnboardingFour()
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
    Column(modifier = Modifier.padding(start = 24.dp, end = 44.dp)) {
        Box(
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
        Box(
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

@Composable
fun OnboardingFour() {
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
            onClick = { /*TODO*/ },
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

@Composable
@Preview
fun PreviewMemo() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            OnboardingRoute(padding = PaddingValues(10.dp))
        }
    }
}
