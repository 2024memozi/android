package com.memozi.diary.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.memozi.designsystem.MemoziTheme

@Composable
fun DiaryScreenDialog(
    onDismissRequest: () -> Unit = {},
    height: Dp,
    content: @Composable () -> Unit = {}
) {
    val width = (LocalConfiguration.current.screenWidthDp * 0.76).dp
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = MemoziTheme.colors.white),
            contentAlignment = Alignment.TopCenter,
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showMemoziDialog() {
    MemoziTheme {

    }
}