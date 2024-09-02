package com.memozi.component.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.memozi.designsystem.MemoziTheme

@Composable
fun MemoziBackground(
    topWeight: Float = 17f,
    bottomWeight: Float = 50f
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(topWeight)
                .background(
                    brush = MemoziTheme.colors.gradientBrush
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(bottomWeight)
                .background(
                    color = MemoziTheme.colors.white
                )
        )
    }
}

@Preview
@Composable
private fun PrviewMemoziBackGround() {
    MemoziTheme {
        MemoziBackground()
    }
}
