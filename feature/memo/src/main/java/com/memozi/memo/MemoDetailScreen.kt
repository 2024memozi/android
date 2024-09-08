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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.memo.model.dummyMemoCategoriesItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var titleValue by remember { mutableStateOf("") }
    var memoValue by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
        ) {
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
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_drop_black),
                    contentDescription = null,
                    modifier =
                        Modifier.clickable {
                            showBottomSheet = true
                        },
                )
            }

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
            value = titleValue,
            onValueChange = { newValue -> titleValue = newValue },
            placeholder = {
                Text(
                    text = "제목",
                    style = MemoziTheme.typography.ssuLight19,
                    color = MemoziTheme.colors.gray04,
                )
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            textStyle = MemoziTheme.typography.ngReg15,
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
                value = memoValue,
                onValueChange = { newValue -> memoValue = newValue },
                placeholder = {
                    Text(
                        text = "메모 내용을 입력하세요!",
                        style = MemoziTheme.typography.ngBold12_140,
                        color = MemoziTheme.colors.gray04,
                    )
                },
                colors =
                    TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
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
                    sheetState.hide()
                    showBottomSheet = false
                }
            },
            dragHandle = null,
            containerColor = Color.White,
            shape = RectangleShape,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "카테고리 변경",
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                    )
                    Image(
                        painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_close),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .size(24.dp)
                                .clickable {
                                    scope.launch {
                                        sheetState.hide()
                                        showBottomSheet = false
                                    }
                                },
                        alignment = Alignment.CenterEnd,
                    )
                }
                Divider(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    color = MemoziTheme.colors.gray03,
                    thickness = 1.dp,
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                ) {
                    val itemsList = dummyMemoCategoriesItems()
                    items(itemsList) { categoryItem ->
                        Column {
                            Text(
                                text = categoryItem.name,
                                style = MemoziTheme.typography.ngReg15,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, top = 8.dp),
                                color = Color.Black,
                            )

                            if (itemsList.lastOrNull() != categoryItem) {
                                Divider(
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp),
                                    color = MemoziTheme.colors.gray01,
                                    thickness = 1.dp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrviewMemoziBackGround() {
    MemoziTheme {
        Box(modifier = Modifier.background(color = MemoziTheme.colors.white)) {
            MemoDetailScreen()
        }
    }
}
