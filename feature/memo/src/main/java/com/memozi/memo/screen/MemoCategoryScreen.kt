package com.memozi.memo.screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memozi.component.button.MemoziButton
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.designsystem.Ssurround
import com.memozi.memo.MemoziCategory
import com.memozi.ui.extension.customClickable
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MemoCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigateMemo: () -> Unit = {}
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is CategorySideEffect.NavigateToCategory -> TODO()
                CategorySideEffect.NavigateToCategoryAdd -> TODO()
                CategorySideEffect.NavigateToMemo -> {
                    navigateMemo()
                }

                CategorySideEffect.NavigateToSettings -> TODO()
            }
        }
    }
    CategoryScreen(viewModel = viewModel, state = state.value, editMode = state.value.editMode)
    Row(
        modifier = Modifier
            .padding(top = 30.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        MemoziButton(
            text = if (state.value.editMode) "저장" else "등록",
            clickEvent = { if (state.value.editMode) viewModel.updateCategory() else viewModel.postCategory() },
            enabled = state.value.btnEnable
        )
    }
    Row(
        modifier = Modifier
            .padding(top = 30.dp, start = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        if (state.value.editMode) {
            MemoziButton(
                text = "삭제",
                clickEvent = { viewModel.deleteCategory() }
            )
        }
    }
}

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    state: CategoryState,
    editMode: Boolean = false
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MemoziTheme.colors.white)
            .padding(bottom = navigationBarHeight)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (editMode) "카테고리 수정" else "카테고리 추가",
                style = MemoziTheme.typography.ssuLight19,
                color = MemoziTheme.colors.black,
                textAlign = TextAlign.Center
            )
        }

        MemoziCategory(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .padding(top = 30.dp)
                .padding(horizontal = 16.dp)
                .background(
                    color = when (state.selectedColor) {
                        0 -> MemoziTheme.colors.mainPink
                        1 -> MemoziTheme.colors.pink
                        2 -> MemoziTheme.colors.yellow01
                        3 -> MemoziTheme.colors.yellow02
                        4 -> MemoziTheme.colors.orange
                        5 -> MemoziTheme.colors.yellowGreen01
                        6 -> MemoziTheme.colors.yellowGreen02
                        7 -> MemoziTheme.colors.mainPurple
                        8 -> MemoziTheme.colors.blue01
                        9 -> MemoziTheme.colors.gray02
                        10 -> MemoziTheme.colors.black
                        11 -> MemoziTheme.colors.white
                        else -> Color.Transparent
                    },
                    shape = RoundedCornerShape(14.dp)
                ),
            imageURL = state.imageUrl,
            title = state.name,
            titleColor = Color(android.graphics.Color.parseColor(state.textColor)),
            textStyle = TextStyle(
                fontFamily = Ssurround,
                fontSize = 32.sp,
                lineHeight = 35.sp,
                letterSpacing = 0.sp
            ),
            textModifier = Modifier
                .padding(bottom = 16.dp)
                .padding(end = 16.dp)
        )

        CategoryTextField(name = state.name, onValueChange = { viewModel.updateName(it) })

        // Color Picker and Image Selection Grid
        ImageAndColorPicker(
            selectedColorIndex = state.selectedColor,
            selectedTextColorIndex = state.selectedText,
            updateUrl = { viewModel.updateImageUrl(it.toString()) },
            updateImageOpt = { viewModel.updateImageOpt(it) },
            updateSelectedTextColorIndex = { viewModel.setSelectedTextColorIndex(it) },
            updateSelectedColorIndex = { viewModel.setSelectedColorIndex(it) }
        )
    }
}

@Composable
fun CategoryTextField(
    onValueChange: (String) -> Unit = { },
    name: String
) {
    var text by remember { mutableStateOf(name) }
    val maxCharCount = 10
    val isError = text.isEmpty()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(21.dp)
        ) {
            BasicTextField(
                value = text,
                maxLines = 1,
                onValueChange = {
                    if (it.length <= maxCharCount && !it.contains(" ")) {
                        text = it
                        onValueChange(text)
                    }
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
                textStyle = MemoziTheme.typography.ngReg15.copy(MemoziTheme.colors.black),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "카테고리 이름",
                            style = MemoziTheme.typography.ngReg15.copy(MemoziTheme.colors.gray05)
                        )
                    }
                    innerTextField()
                }
            )
            Text(
                text = "${text.length}/$maxCharCount",
                modifier = Modifier.fillMaxHeight(),
                style = MemoziTheme.typography.ngReg15.copy(color = MemoziTheme.colors.gray04),
                textAlign = TextAlign.End
            )

            if (text.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_x_white_13),
                    tint = Color.Unspecified,
                    contentDescription = "삭제버튼",
                    modifier = Modifier.customClickable {
                        text = ""
                        onValueChange(text)
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(end = if (text.isNotEmpty()) 16.dp else 0.dp)
                .shadow(elevation = 1.dp)
                .background(color = if (isError) MemoziTheme.colors.red else Color.Transparent)
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (text.isEmpty()) {
            Text(
                text = "카테고리 이름을 입력해주세요!",
                style = MemoziTheme.typography.ngReg8.copy(color = MemoziTheme.colors.red)
            )
        }
    }
}

@Composable
fun ImageAndColorPicker(
    selectedColorIndex: Int,
    selectedTextColorIndex: Int,
    updateUrl: (Uri?) -> Unit = {},
    updateImageOpt: (Int) -> Unit = {},
    updateSelectedColorIndex: (Int) -> Unit = {},
    updateSelectedTextColorIndex: (Int) -> Unit = {}
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        PhotoPicker(updateUrl = updateUrl, updateImageOpt = updateImageOpt)
        ColorPicker(
            updateUrl = updateUrl,
            selectedColorIndex = selectedColorIndex
        ) { updateSelectedColorIndex(it) }
        TextColorPicker(selectedTextColorIndex = selectedTextColorIndex) {
            updateSelectedTextColorIndex(it)
        }
    }
}

@Composable
fun PhotoPicker(
    updateUrl: (Uri?) -> Unit,
    updateImageOpt: (Int) -> Unit = {}
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            updateUrl(uri)
        }
    }
    val imageUrls = listOf(
        "",
        "https://github.com/user-attachments/assets/f463d586-5cac-4a4f-852c-981688b31279",
        "https://github.com/user-attachments/assets/281592eb-dcd1-4e6d-9502-b28e2bb759fe",
        "https://github.com/user-attachments/assets/7e5b82df-b138-431b-9a5e-15723dac08a9",
        "https://github.com/user-attachments/assets/80e9dd1f-e00f-4a45-8fc9-ab34947d0fa0",
        "https://github.com/user-attachments/assets/92222695-6dfb-4f6e-bb1d-4cb3fc78c733",
        "https://github.com/user-attachments/assets/74a686ba-347d-47b6-90f7-d126cc4836e8",
        "https://github.com/user-attachments/assets/307ac07c-8990-41d6-aaa9-3d5d154adf30"
    )

    Text(text = "사진 배경", style = MemoziTheme.typography.ngBold14)
    Spacer(modifier = Modifier.height(8.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(8) { index ->
            if (index == 0) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .aspectRatio(1f)
                        .background(
                            color = MemoziTheme.colors.gray02,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .customClickable {
                            updateImageOpt(0)
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            ).apply {
                                type = "image/*"
                            }
                            launcher.launch(intent)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera_gray_24),
                        contentDescription = "Camera Icon",
                        tint = MemoziTheme.colors.white
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .aspectRatio(1f)
                        .background(
                            color = MemoziTheme.colors.gray02,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .customClickable {
                            updateImageOpt(1)
                            updateUrl(imageUrls[index].toUri())
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_category_01 + index),
                        contentDescription = "카테고리 선택",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun ColorPicker(
    updateUrl: (Uri?) -> Unit,
    selectedColorIndex: Int,
    updateSelectedColorIndex: (Int) -> Unit
) {
    val colorImageUrls = listOf(
        "https://github.com/user-attachments/assets/d81199e5-a6d7-49a3-87e5-9453b2e73109",
        "https://github.com/user-attachments/assets/fad22470-6026-44be-a1c1-dc5dac9b359c",
        "https://github.com/user-attachments/assets/bf3a7e71-0e9e-4154-b394-4bc7ac3a73f6",
        "https://github.com/user-attachments/assets/baa5d621-5154-4be7-a029-20dcb3e7b837",
        "https://github.com/user-attachments/assets/efaba9d6-b58a-4967-893f-6642e1d58b6b",
        "https://github.com/user-attachments/assets/9e2e9b0d-91b8-4099-a150-597d2a647f3e",
        "https://github.com/user-attachments/assets/2ff64136-aefb-4351-afbd-d39900a64251",
        "https://github.com/user-attachments/assets/c61574fd-a380-4625-a89c-958aed65bce5",
        "https://github.com/user-attachments/assets/a12f63c6-daf8-495a-afaa-d99eb2b2b29b",
        "https://github.com/user-attachments/assets/9b27c7a9-f680-480a-851d-4736b9a1cd76",
        "https://github.com/user-attachments/assets/e1835347-31b9-4c7d-bbf6-e0838594c3ad",
        "https://github.com/user-attachments/assets/408ed494-73ee-4316-8b83-b8e336413fd0"
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "단색 배경", style = MemoziTheme.typography.ngBold14)
    Spacer(modifier = Modifier.height(8.dp))

    // Unified Color Picker Grid with spacing between rows
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 40.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(12) { index ->
            val color = when (index) {
                0 -> MemoziTheme.colors.mainPink
                1 -> MemoziTheme.colors.pink
                2 -> MemoziTheme.colors.yellow01
                3 -> MemoziTheme.colors.yellow02
                4 -> MemoziTheme.colors.orange
                5 -> MemoziTheme.colors.yellowGreen01
                6 -> MemoziTheme.colors.yellowGreen02
                7 -> MemoziTheme.colors.mainPurple
                8 -> MemoziTheme.colors.blue01
                9 -> MemoziTheme.colors.gray02
                10 -> MemoziTheme.colors.black
                11 -> MemoziTheme.colors.white
                else -> Color.Transparent
            }

            val borderColor = if (selectedColorIndex == index) {
                MemoziTheme.colors.cautionRed
            } else {
                Color.Transparent
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(1 / 9f)
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = if (color == MemoziTheme.colors.white && selectedColorIndex != index) MemoziTheme.colors.gray02 else borderColor,
                        shape = CircleShape
                    )
                    .background(color = color, shape = CircleShape)
                    .customClickable {
                        updateUrl(colorImageUrls[index].toUri())
                        updateSelectedColorIndex(index)
                    }
            )
        }
    }
}

@Composable
fun TextColorPicker(
    selectedTextColorIndex: Int,
    updateSelectedTextColorIndex: (Int) -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "텍스트 색", style = MemoziTheme.typography.ngBold14)
    Spacer(modifier = Modifier.height(8.dp))
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 40.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(2) { index ->
            val textColors = when (index) {
                0 -> MemoziTheme.colors.black
                else -> {
                    MemoziTheme.colors.white
                }
            }

            val borderColor = if (selectedTextColorIndex == index) {
                MemoziTheme.colors.cautionRed
            } else {
                Color.Transparent
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(1 / 9f)
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = if (textColors == MemoziTheme.colors.white && selectedTextColorIndex != index) MemoziTheme.colors.gray02 else borderColor,
                        shape = CircleShape
                    )
                    .background(color = textColors, shape = CircleShape)
                    .customClickable {
                        updateSelectedTextColorIndex(index)
                    }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoryScreen() {
    MemoziTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            MemoCategoryScreen()
        }
    }
}
