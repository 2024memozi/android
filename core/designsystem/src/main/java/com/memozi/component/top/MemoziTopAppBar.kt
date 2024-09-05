package com.memozi.component.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.ui.extension.customClickable

@Composable
fun MemoziTopAppbar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    btnText: String = "일기",
    navigateToFirst: () -> Unit = {},
    navigateToSetting: () -> Unit = {},
    innerComposable: @Composable () -> Unit
) {
    val insets = WindowInsets.statusBars.asPaddingValues()
    Column {
        Spacer(modifier = Modifier.height(insets.calculateTopPadding()))
        Row(
            modifier = modifier
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "MEMO : ZI",
                style = MemoziTheme.typography.appnameBold15,
                modifier = Modifier.padding(start = 18.dp)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            )
            Text(
                text = btnText,
                style = MemoziTheme.typography.appnameBold13,
                modifier = Modifier.customClickable(onClick = navigateToFirst)
            )
            Text(text = " | ", style = MemoziTheme.typography.appnameBold13)
            Text(
                text = "설정",
                modifier = Modifier
                    .customClickable(onClick = navigateToSetting)
                    .padding(end = 21.dp),
                style = MemoziTheme.typography.appnameBold13
            )
        }
        innerComposable()
    }
}

@Preview
@Composable
private fun PreviewMemoziTopAppBar() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            MemoziTopAppbar {
            }
        }
    }
}
