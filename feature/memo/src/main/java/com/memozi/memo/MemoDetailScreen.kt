import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.memozi.component.button.CheckBoxSelected
import com.memozi.component.button.CheckBoxUnSelected
import com.memozi.designsystem.MemoziTheme
import com.memozi.memo.MemoViewModel
import com.memozi.memo.model.CheckBox
import com.memozi.memo.model.dummyMemoCategoriesItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailScreen(
    viewmodel: MemoViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var titleValue by remember { mutableStateOf("") }
    var memoValue by remember { mutableStateOf("") }
    var checkBoxItems by remember { mutableStateOf<List<Pair<Boolean, String>>>(listOf()) } // 상태와 텍스트를 저장
    var isVisible by remember { mutableStateOf(true) }

    fun convertToCheckBoxList(checkBoxItems: List<Pair<Boolean, String>>): List<CheckBox> {
        return checkBoxItems.mapIndexed { index, pair ->
            CheckBox(
                id = index,
                content = pair.second,
                checked = pair.first
            )
        }
    }

    val isEnabled =
        titleValue.isNotEmpty() && (checkBoxItems.isNotEmpty() || memoValue.isNotEmpty())

    Column(
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "카테고리",
                        style = MemoziTheme.typography.ssuLight19,
                        color = MemoziTheme.colors.black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_drop_black),
                        contentDescription = null,
                        modifier =
                        Modifier.clickable {
                            showBottomSheet = true
                        }
                    )
                }
                if (isVisible) {
                    Button(
                        onClick = {
                            viewmodel.putmemo(
                                title = titleValue,
                                content = memoValue,
                                checkBoxs = convertToCheckBoxList(checkBoxItems)
                            )
                        },
                        modifier =
                        Modifier
                            .width(68.dp)
                            .height(34.dp)
                            .align(Alignment.CenterEnd),
                        shape = RoundedCornerShape(8.dp),
                        enabled = isEnabled,
                        colors =
                        ButtonDefaults.buttonColors(
                            contentColor = MemoziTheme.colors.white,
                            containerColor = if (isEnabled) MemoziTheme.colors.mainPurple else MemoziTheme.colors.gray02,
                            disabledContentColor = MemoziTheme.colors.white,
                            disabledContainerColor = MemoziTheme.colors.gray02
                        ),
                        contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "등록",
                            style = MemoziTheme.typography.ssuLight12
                        )
                    }
                }
            }
        }

        TextField(
            value = titleValue,
            onValueChange = { newValue -> titleValue = newValue },
            placeholder = {
                Text(
                    text = "제목",
                    style = MemoziTheme.typography.ssuLight19,
                    color = MemoziTheme.colors.gray04
                )
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            textStyle = MemoziTheme.typography.ngReg15,
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MemoziTheme.colors.gray02,
                unfocusedIndicatorColor = MemoziTheme.colors.gray02,
                disabledIndicatorColor = MemoziTheme.colors.gray02
            ),
            shape = RectangleShape
        )
        LazyColumn(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 2.dp)
        ) {
            item {
                TextField(
                    value = memoValue,
                    onValueChange = { newValue -> memoValue = newValue },
                    placeholder = {
                        Text(
                            text = "메모 내용을 입력하세요!",
                            style = MemoziTheme.typography.ngBold12_140,
                            color = MemoziTheme.colors.gray04
                        )
                    },
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    // 텍스트 필드 패딩
                    colors =
                    TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RectangleShape
                )
            }
            itemsIndexed(checkBoxItems) { index, item ->
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (item.first) {
                        CheckBoxSelected {
                            checkBoxItems =
                                checkBoxItems.toMutableList().apply {
                                    this[index] = item.copy(first = !item.first)
                                }
                        }
                    } else {
                        CheckBoxUnSelected {
                            checkBoxItems =
                                checkBoxItems.toMutableList().apply {
                                    this[index] = item.copy(first = !item.first)
                                }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = item.second,
                        onValueChange = { newText ->
                            checkBoxItems =
                                checkBoxItems.toMutableList().apply {
                                    this[index] = item.copy(second = newText)
                                }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MemoziTheme.typography.ngReg12_170,
                        colors =
                        TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RectangleShape
                    )
                }
            }
        }

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    BorderStroke(1.dp, MemoziTheme.colors.gray02)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Image(
                painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_emptycheck),
                contentDescription = null,
                modifier =
                Modifier.clickable {
                    checkBoxItems = checkBoxItems + Pair(false, "")
                }
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
            shape = RectangleShape
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "카테고리 변경",
                        style = MemoziTheme.typography.ngBold15,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
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
                        alignment = Alignment.CenterEnd
                    )
                }
                Divider(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = MemoziTheme.colors.gray03,
                    thickness = 1.dp
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp)
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
                                color = Color.Black
                            )

                            if (itemsList.lastOrNull() != categoryItem) {
                                Divider(
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp),
                                    color = MemoziTheme.colors.gray01,
                                    thickness = 1.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun dropdownMenu(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier =
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More Options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
            Modifier
                .width(84.dp)
                .height(60.dp)
        ) {
            DropdownMenuItem(
                text = { Text("수정하기") },
                onClick = {
                    expanded = false
                    onEditClick()
                }
            )
            DropdownMenuItem(
                text = { Text("삭제하기") },
                onClick = {
                    expanded = false
                    onDeleteClick()
                }
            )
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
