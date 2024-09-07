import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun memoDetailScreen() {
    // BottomSheet 상태 관리
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    // 화면 전체를 채우는 레이아웃
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        // Box로 중앙에 텍스트와 이미지를 배치하고, 버튼은 오른쪽 끝에 배치
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        ) {
            // 텍스트와 이미지를 중앙에 배치하는 Row
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "카테고리",
                    style = MemoziTheme.typography.ssuLight19,
                    color = MemoziTheme.colors.black,
                )
                Spacer(modifier = Modifier.width(8.dp)) // 텍스트와 이미지 사이 간격
                Image(
                    painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_drop_black),
                    contentDescription = null,
                    modifier =
                    Modifier.clickable {
                        scope.launch {
                            sheetState.show()
                        }
                    },
                )
            }

            // 버튼을 오른쪽 끝에 배치
            Button(
                onClick = {},
                modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .width(68.dp)
                    .height(34.dp),
                shape = RoundedCornerShape(8.dp),
                colors =
                ButtonDefaults.buttonColors(
                    contentColor = MemoziTheme.colors.white,
                    containerColor = MemoziTheme.colors.mainPurple,
                    disabledContentColor = MemoziTheme.colors.white,
                    disabledContainerColor = MemoziTheme.colors.gray02,
                ),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "등록",
                    style = MemoziTheme.typography.ssuLight12,
                )
            }
        }

        TextField(
            value = "",
            onValueChange = {}, // 사용자가 입력한 값 처리
            placeholder = {
                Text(
                    text = "제목",
                    style = MemoziTheme.typography.ssuLight19,
                    color = MemoziTheme.colors.gray04, // 힌트 색상
                )
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textStyle = MemoziTheme.typography.ngReg15, // 입력 텍스트 스타일
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MemoziTheme.colors.gray02,
                unfocusedIndicatorColor = MemoziTheme.colors.gray02,
                disabledIndicatorColor = MemoziTheme.colors.gray02,
            ),
            shape = RectangleShape,
        )

        Column(
            modifier =
            Modifier
                .fillMaxSize(1f)
                .padding(top = 16.dp),
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "메모 내용을 입력하세요!",
                        style = MemoziTheme.typography.ngBold12_140,
                        color = MemoziTheme.colors.gray04, // 힌트 색상
                    )
                },
                colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RectangleShape,
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide() // 빈 공간을 클릭하면 바텀시트를 닫음
                    showBottomSheet = false
                }
            },
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = "바텀시트 내용")
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_drop_black),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(100.dp)
                        .clickable {
                            scope.launch {
                                sheetState.hide() // 이미지를 클릭하면 바텀시트를 닫음
                                showBottomSheet = false
                            }
                        },
                )
            }
        }
    }
}

@Preview
@Composable
private fun PrviewMemoziBackGround() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
//            memoDetailScreen()

        }

    }
}
