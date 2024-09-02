package com.memozi.memo.screen

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memozi.designsystem.MemoziTheme
import com.memozi.memo.MemoziCategory
import com.memozi.ui.extension.customClickable

@Composable
fun MemoCategoryScreen() {
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

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
                .background(color = MemoziTheme.colors.black, shape = RoundedCornerShape(14.dp)),
            imageURL = "https://github.com/user-attachments/assets/6443c2d2-c1f9-43a5-9d52-840a6b765fcb",
            title = "",
            titleColor = MemoziTheme.colors.white
        )

        // Text Field for Category Name
        TextField(
            value = "", // Add state management for the text field
            onValueChange = {},
            label = { Text(text = "카테고리 이름") },
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )

        // Color Picker and Image Selection Grid
        ImageAndColorPicker()
    }
    Row(
        modifier = Modifier
            .padding(top = 30.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        MemoziButton()
    }
}

@Composable
fun ImageAndColorPicker() {
    val (selectedColorIndex, setSelectedColorIndex) = remember { mutableStateOf(-1) }
    val (selectedTextColorIndex, setSelectedTextColorIndex) = remember { mutableStateOf(-1) }
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
                            .customClickable { openImagePicker() }, // Trigger image picker
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = com.memozi.designsystem.R.drawable.ic_camera_gray_24),
                            contentDescription = "Camera Icon",
                            tint = MemoziTheme.colors.white
                        )
                    }
                } else {
                    // For other indices, display images or placeholders
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .aspectRatio(1f)
                            .background(
                                color = MemoziTheme.colors.gray02,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .customClickable {
                                // Handle other image clicks if needed
                            }
                    )
                }
            }
        }
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

                // Set border color based on selection
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
                val borderColor =
                    if (selectedTextColorIndex == index) {
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
                            setSelectedTextColorIndex(index)
                        }
                )
            }
        }
    }
}

// Function to open image picker
fun openImagePicker() {
    // Implement image picker functionality
}

@Composable
fun MemoziButton(
    modifier: Modifier = Modifier,
    text: String = "저장",
    textColor: Color = MemoziTheme.colors.white,
    clickEvent: () -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .height(26.dp)
            .width(67.dp)
            .background(
                color = if (enabled) MemoziTheme.colors.mainPurple else MemoziTheme.colors.gray02,
                shape = RoundedCornerShape(8.dp)
            )
            .customClickable {
                if (enabled) clickEvent()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MemoziTheme.typography.ssuLight12,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
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
