package com.memozi.memo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.memo.model.CheckBox
import com.memozi.memo.model.Memo
import com.memozi.memo.model.MemoDummy

@Composable
fun MemoItemCard(modifier: Modifier = Modifier, memo: Memo, checkBoxClick: (Int) -> Unit = {}) {
    var checkBoxes by remember { mutableStateOf(memo.checkBoxes) }

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
                text = memo.updatedAt + " " + memo.dayOfWeek,
                style = MemoziTheme.typography.ngReg11,
                color = MemoziTheme.colors.gray03
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (checkBoxes.isEmpty()) {
            Text(
                text = memo.content,
                style = MemoziTheme.typography.ngReg12_140,
                color = MemoziTheme.colors.gray05
            )
        } else {
            checkBoxes.take(2).forEachIndexed { index, checkBox ->
                MemoCheckBox(
                    CheckBox = checkBox,
                    checkBoxClick = {
                        checkBoxes = checkBoxes.map {
                            if (it.id == checkBox.id) {
                                it.copy(checked = !it.checked)
                            } else {
                                it
                            }
                        }
                        checkBoxClick(checkBox.id)
                    }
                )
                if (index == 0) Spacer(modifier = Modifier.height(8.dp))
            }
            if (checkBoxes.size == 1) {
                Text(
                    text = memo.content,
                    style = MemoziTheme.typography.ssuLight12,
                    color = MemoziTheme.colors.gray05,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MemoCheckBox(CheckBox: CheckBox, checkBoxClick: () -> Unit = {}) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (CheckBox.checked) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(brush = MemoziTheme.colors.gradientBrush)
                    .clickable { checkBoxClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_mark),
                    contentDescription = "Check Icon",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MemoziTheme.typography.ngReg12_140,
                text = CheckBox.content
            )
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(MemoziTheme.colors.gray02)
                    .clickable { checkBoxClick() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = MemoziTheme.typography.ngReg12_140,
                text = CheckBox.content
            )
        }
    }
}

@Preview
@Composable
fun PreviewMemo() {
    MemoziTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            MemoItemCard(
                modifier = Modifier,
                memo = MemoDummy(),
                checkBoxClick = {}
            )
        }
    }
}
