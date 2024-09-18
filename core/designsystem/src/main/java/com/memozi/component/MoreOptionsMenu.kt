package com.memozi.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.memozi.designsystem.MemoziTheme

@Composable
fun MoreOptionsMenu(
    modifier: Modifier,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More Options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        "수정하기",
                        style = MemoziTheme.typography.ngReg13,
                        color = MemoziTheme.colors.black
                    )
                },
                onClick = {
                    onEditClick()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        "삭제하기",
                        style = MemoziTheme.typography.ngReg13,
                        color = MemoziTheme.colors.cautionRed
                    )
                },
                onClick = {
                    onDeleteClick()
                    expanded = false
                }
            )
        }
    }
}
