package com.memozi.component.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun MemoziSearchTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    placeholder: String = "메모를 검색해 보세요!",
    maxLines: Int = 1,
    maxLength: Int = 10,
    textStyle: TextStyle = MemoziTheme.typography.ssuLight12,
    onValueChange: (String) -> Unit = { _ -> },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var value by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(8.dp)

    Row(
        modifier = modifier
            .background(shape = shape, color = MemoziTheme.colors.white),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .height(26.dp)
                .width(26.dp)
                .padding(start = 8.dp),
            painter = painterResource(id = R.drawable.ic_serach),
            contentDescription = null
        )

        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength && !it.contains(" ")) {
                    value = it
                    onValueChange(it)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp)
                .onFocusChanged { isFocused = it.isFocused }
                .wrapContentHeight(Alignment.CenterVertically),
            textStyle = textStyle.copy(color = MemoziTheme.colors.black),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = maxLines == 1,
            cursorBrush = MemoziTheme.colors.gradientBrush,
            decorationBox = { innerTextField ->
                if (value.isEmpty() && !isFocused) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = MemoziTheme.colors.gray05)
                    )
                }
                innerTextField()
            }
        )
    }
}

@Composable
@Preview
fun PreviewMemoziSearchText() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.black)) {
            MemoziSearchTextField()
        }
    }
}
