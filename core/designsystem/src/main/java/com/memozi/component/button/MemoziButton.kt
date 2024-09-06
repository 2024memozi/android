package com.memozi.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.ui.extension.customClickable

@Composable
fun MemoziButton(
    modifier: Modifier = Modifier
        .height(26.dp)
        .width(67.dp),
    text: String = "저장",
    textColor: Color = MemoziTheme.colors.white,
    clickEvent: () -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .background(
                color = if (enabled) MemoziTheme.colors.mainPurple else MemoziTheme.colors.gray02,
                shape = RoundedCornerShape(8.dp)
            )
            .customClickable {
                if (enabled) clickEvent()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MemoziTheme.typography.ssuLight12,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
