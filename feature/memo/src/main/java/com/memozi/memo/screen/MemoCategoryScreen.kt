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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun MemoCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MemoziTheme.colors.white)
            .padding(bottom = navigationBarHeight)
    ) {
        // Top title
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "카테고리 추가",
                style = MemoziTheme.typography.ssuLight19,
                color = MemoziTheme.colors.black,
                textAlign = TextAlign.Center
            )
        }

        // Main image section
        MemoziCategory(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .padding(top = 30.dp)
                .padding(horizontal = 16.dp)
                .background(
                    color = MemoziTheme.colors.black,
                    shape = RoundedCornerShape(14.dp)
                ),
            imageURL = state.value.imageUrl,
            title = state.value.name,
            titleColor = Color( android.graphics.Color.parseColor(state.value.textColor)),
            textStyle = TextStyle(
                fontFamily = Ssurround,
                fontSize = 32.sp,
                lineHeight = 35.sp, // 100%
                letterSpacing = 0.sp
            ),
            textModifier = Modifier
                .padding(bottom = 16.dp)
                .padding(end = 16.dp),
        )


        CategoryTextField(onValueChange = { viewModel.updateName(it) })

        // Color Picker and Image Selection Grid
        ImageAndColorPicker(
            imageUri = state.value.imageUrl.toUri(),
            updateUrl = { viewModel.updateImageUrl(it.toString()) },
            updateTextColor = {viewModel.updateTextColor(it)}
        )
    }
    Row(
        modifier = Modifier
            .padding(top = 30.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        MemoziButton(clickEvent = { viewModel.postCategory() })
    }
}

@Composable
fun CategoryTextField(
    onValueChange: (String) -> Unit = { }
) {
    var text by remember { mutableStateOf("") }
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
                onValueChange = {
                    if (it.length <= maxCharCount && !it.contains(" ")) {
                        text = it
                        onValueChange(text)
                    }
                },
                modifier = Modifier
                    .fillMaxHeight()
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
            Spacer(modifier = Modifier.weight(1f))
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
                    modifier = Modifier
                        .customClickable { text = "" }
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
    imageUri: Uri,
    updateUrl: (Uri?) -> Unit = {},
    updateTextColor:(Int)-> Unit={},
    clickEvent: (Uri?) -> Unit = {}
) {
    val (selectedColorIndex, setSelectedColorIndex) = remember { mutableStateOf(-1) }
    val (selectedTextColorIndex, setSelectedTextColorIndex) = remember { mutableStateOf(-1) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            updateUrl(uri)
            clickEvent(uri) // Pass the selected image URI back to the parent function
        }
    }
    val imageUrls = listOf(
        "",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/182b72d4-b2dc-48cd-b29c-d3e783bcb8ef_img1.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/6623d807-ca63-47ec-9b72-9f5ef7ec0ad9_img2.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/931aeb54-f316-4032-adf1-0b275570f4a2_img3.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/0003d64c-e42b-490d-84d5-58eed8f7b550_img5.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/210a8361-9d77-4b2e-a8e2-26cf1ad1c74d_img1_%282%29.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/8b8b69b0-a0c6-40d6-8191-540ef896106c_testimg.png",
        "https://memozi.s3.ap-northeast-2.amazonaws.com/uploads/e6124a22-cd95-4283-b772-7c79e2005b84_img7.png"
    )

    val colorImageUrls = listOf(
        ""
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = "사진 배경", style = MemoziTheme.typography.ngBold14)
        Spacer(modifier = Modifier.height(8.dp))

        // Image Picker Grid
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
//

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
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = if (color == MemoziTheme.colors.white && selectedColorIndex != index) MemoziTheme.colors.gray02 else borderColor,
                            shape = CircleShape
                        )
                        .background(color = color, shape = CircleShape)
                        .customClickable {
                            setSelectedColorIndex(index) // Update selected index
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "텍스트 색", style = MemoziTheme.typography.ngBold14)
        Spacer(modifier = Modifier.height(8.dp))
        // Text Color Picker using Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val textColors = listOf(
                MemoziTheme.colors.black,
                MemoziTheme.colors.white
            )

            textColors.forEachIndexed { index, color ->
                val borderColor = if (selectedTextColorIndex == index) {
                    MemoziTheme.colors.cautionRed
                } else {
                    Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = if (color == MemoziTheme.colors.white && selectedTextColorIndex != index) MemoziTheme.colors.gray02 else borderColor,
                            shape = CircleShape
                        )
                        .background(color = color, shape = CircleShape)
                        .customClickable {
                            updateTextColor(index)
                            setSelectedTextColorIndex(index)
                        }
                )
            }
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
