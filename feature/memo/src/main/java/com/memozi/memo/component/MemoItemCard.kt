package com.memozi.memo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.component.button.CheckBoxSelected
import com.memozi.component.button.CheckBoxUnSelected
import com.memozi.designsystem.MemoziTheme
import com.memozi.memo.model.CheckBox
import com.memozi.memo.model.Memo
import com.memozi.memo.model.MemoDummy

@Composable
fun MemoItemCard(modifier: Modifier, memo: Memo) {
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
                text = memo.dayOfWeek,
                style = MemoziTheme.typography.ngReg11,
                color = MemoziTheme.colors.gray03
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (memo.checkBoxes.isEmpty())
            Text(
                text = memo.content,
                style = MemoziTheme.typography.ssuLight12,
                color = MemoziTheme.colors.gray05
            )
        else {
            if (memo.checkBoxes.size >= 2) {
                MemoCheckBox(CheckBox = memo.checkBoxes[0])
                Spacer(modifier = Modifier.height(8.dp))
                MemoCheckBox(CheckBox = memo.checkBoxes[1])
            } else MemoCheckBox(CheckBox = memo.checkBoxes[0])

        }
    }
}

@Composable
fun MemoCheckBox(CheckBox: CheckBox) {
    Row {
        if (CheckBox.checked) {
            CheckBoxSelected {

            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MemoziTheme.typography.ssuLight12,
                text = CheckBox.content,
            )
        } else {
            CheckBoxUnSelected {

            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                style = MemoziTheme.typography.ssuLight12,
                text = CheckBox.content,
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
                memo = MemoDummy()
            )
        }
    }
}