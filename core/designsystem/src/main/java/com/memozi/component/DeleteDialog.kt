package com.memozi.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R

@Composable
fun CustomDialogContent() {
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .aspectRatio(272f / 132f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_caution),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "삭제된 메모는 복구할 수 없습니다",
                style = MemoziTheme.typography.ngReg12_140,
                color = MemoziTheme.colors.black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "메모를 삭제하시겠습니까?",
                style = MemoziTheme.typography.ngReg12_140,
                color = MemoziTheme.colors.black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MemoziTheme.colors.gray01
                    ),
                    modifier =
                    Modifier
                        .width(88.dp)
                        .height(30.dp)
                ) {
                    Text(
                        text = "취소",
                        style = MemoziTheme.typography.ngReg12_140,
                        color = MemoziTheme.colors.gray04,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.width(35.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MemoziTheme.colors.cautionRed
                    ),
                    modifier =
                    Modifier
                        .width(88.dp)
                        .height(30.dp)
                ) {
                    Text(
                        text = "삭제",
                        style = MemoziTheme.typography.ngReg12_140,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    MemoziTheme {
        CustomDialogContent()
    }
}
