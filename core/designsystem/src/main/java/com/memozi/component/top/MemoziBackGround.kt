package com.memozi.component.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.memozi.designsystem.MemoziTheme

@Composable
fun MemoziBackGround() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(17f)
                .background(
                    brush = MemoziTheme.colors.gradientBrush
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(50f)
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
        MemoziBackGround()
    }
}
