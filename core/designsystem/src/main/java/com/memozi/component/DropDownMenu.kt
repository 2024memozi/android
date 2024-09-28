package com.memozi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.ui.extension.customClickable

@Composable
fun DropDownMenu(
    modifier: Modifier,
    topPaddingValues: PaddingValues = PaddingValues(top = 32.dp),
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onlyDelete: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(topPaddingValues),
        horizontalAlignment = Alignment.End
    ) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "More Options",
            modifier = modifier
                .customClickable { expanded = !expanded }
        )

        if (expanded) {
            Row {
                Box(
                    modifier = Modifier
                        .shadow(
                            4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) // elevation 효과를 위해 shadow 추가
                        .background(
                            color = MemoziTheme.colors.white,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!onlyDelete) {
                            Text(
                                text = "수정하기",
                                style = MemoziTheme.typography.ngReg13,
                                modifier = Modifier
                                    .padding(vertical = 7.dp)
                                    .clickable { onEditClick() }
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(MemoziTheme.colors.gray02)
                            )
                        }
                        Text(
                            text = "삭제하기",
                            style = MemoziTheme.typography.ngReg13,
                            color = MemoziTheme.colors.red,
                            modifier = Modifier
                                .padding(vertical = 7.dp)
                                .clickable { onDeleteClick() }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}
