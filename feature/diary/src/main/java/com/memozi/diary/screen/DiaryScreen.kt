package com.memozi.diary.screen

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.memozi.component.DropDownMenu
import com.memozi.component.top.MemoziBackground
import com.memozi.designsystem.MemoziTheme
import com.memozi.designsystem.R
import com.memozi.diary.model.Diary
import com.memozi.diary.screen.component.DiaryScreenDialog
import com.memozi.diary.screen.component.WeekHeader
import com.memozi.diary.utils.CalendarUtils
import com.memozi.ui.extension.customClickable
import com.memozi.ui.lifecycle.LaunchedEffectWithLifecycle
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    diaryViewModel: DiaryViewModel = hiltViewModel(),
    navigateToMemo: () -> Unit = {},
    navigateToSetting: () -> Unit = {}
) {
    val diaryState by diaryViewModel.uiState.collectAsStateWithLifecycle()

    var listSelectedState by remember { mutableStateOf(true) }
    var calendarSelectedState by remember { mutableStateOf(false) }
    var diaryWriteState by remember { mutableStateOf(false) }
    val maxDiaryLength = 100
    var diaryContent by remember { mutableStateOf("") }
    var locationDialogState by remember { mutableStateOf(false) }
    val onChangedLocationDialogState: (Boolean) -> Unit =
        { newValue -> locationDialogState = newValue }
    val isDiaryAvailable by remember(diaryContent) { mutableStateOf(diaryContent.isNotEmpty()) }
    var isDiaryWritten by remember { mutableStateOf(false) }
    val isDiaryExist by remember { mutableStateOf(true) }
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    var year by remember { mutableIntStateOf(LocalDate.now().year) }
    var month by remember { mutableIntStateOf(LocalDate.now().monthValue) }
    var location by remember { mutableStateOf("") }
    var isLocationExist by remember { mutableStateOf(false) }
    val onChangedLocation: (String) -> Unit = { newValue -> location = newValue }
    val onChangedLocationExist: (Boolean) -> Unit = { newValue -> isLocationExist = newValue }
    var calendarBottomSheetState by remember { mutableStateOf(false) }
    val isDiaryExistDay by remember { mutableStateOf(false) }

    LaunchedEffectWithLifecycle {
        diaryViewModel.getDiary()
        diaryViewModel.getName()
    }

    MemoziBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = navigationBarHeight)
            .padding(20.dp)
    ) {
        DiaryFeedTopAppBar(
            navigateToMemo = navigateToMemo,
            navigateToSetting = navigateToSetting
        )

        DiaryFeedGreeting(userName = diaryState.name)

        SelectDisplayType(
            listSelectedState = listSelectedState,
            onChangedListSelectedState = { listSelectedState = it },
            calendarSelectedState = calendarSelectedState,
            onChangedCalendarSelectedState = { calendarSelectedState = it },
            onChangedCalendarBottomSheetState = { calendarBottomSheetState = it }
        )

        if (!isDiaryWritten) {
            DiaryFeedWriteCard(
                diaryWriteState = diaryWriteState,
                onChangeDiaryWriteState = { diaryWriteState = it },
                maxDiaryLength = maxDiaryLength,
                diaryContent = diaryContent,
                onChangedDiaryContent = { diaryContent = it },
                onChangedLocationDialogState = onChangedLocationDialogState,
                location = location,
                isLocationExist = isLocationExist,
                isDiaryAvailable = isDiaryAvailable,
                onChangedDiaryWritten = { isDiaryWritten = it },
                postDiary = { content ->
                    diaryViewModel.postDiary(content = content)
                },
                updateUrl = { diaryViewModel.updateUri(it.toString()) },
                imageURL = diaryState.image
            )
        }

        if (isDiaryExist) {
            DiaryFeedDisplayCard(
                diaryState.diaryList,
                editEvent = {},
                deleteEvent = { diaryViewModel.delteDiary(it) }
            )
        }
    }

    if (locationDialogState) {
        DiaryScreenDialog(
            height = (LocalConfiguration.current.screenHeightDp * 0.207).dp,
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "위치 추가",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = MemoziTheme.colors.black,
                        style = MemoziTheme.typography.ngBold12_140
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = MemoziTheme.colors.gray02)
                    )
                    Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 0.207 * 0.25).dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = (LocalConfiguration.current.screenWidthDp * 0.76 * 0.118).dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_diary_feed_pin),
                            contentDescription = null
                        )
                        BasicTextField(
                            value = location,
                            onValueChange = onChangedLocation,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = (LocalConfiguration.current.screenWidthDp * 0.76 * 0.118).dp)
                            .height(1.dp)
                            .background(color = MemoziTheme.colors.gray07)
                    )
                    Spacer(Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = (LocalConfiguration.current.screenWidthDp * 0.76 * 0.118).dp)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height((LocalConfiguration.current.screenHeightDp * 0.041).dp)
                                .width((LocalConfiguration.current.screenWidthDp * 0.244).dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = MemoziTheme.colors.gray01)
                                .clickable(onClick = { onChangedLocationDialogState(false) }),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "취소",
                                color = MemoziTheme.colors.gray04,
                                style = MemoziTheme.typography.ngReg12_140
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .height((LocalConfiguration.current.screenHeightDp * 0.041).dp)
                                .width((LocalConfiguration.current.screenWidthDp * 0.244).dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = MemoziTheme.colors.mainPurple)
                                .clickable(
                                    onClick = {
                                        onChangedLocationDialogState(false)
                                        onChangedLocationExist(true)
                                        diaryViewModel.updateLocation(location = location)
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "확인",
                                color = MemoziTheme.colors.white,
                                style = MemoziTheme.typography.ngReg12_140
                            )
                        }
                    }
                }
            }
        )
    }

    if (calendarBottomSheetState) {
        val height = (LocalConfiguration.current.screenHeightDp * (0.144f)).dp
        BottomSheetScaffold(
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 20.dp)
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_back),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    val (newYear, newMonth) = CalendarUtils.showLastMonth(
                                        year,
                                        month
                                    )
                                    year = newYear
                                    month = newMonth
                                }
                        )
                        Text(
                            text = "${year}년 ${month}월 달력",
                            style = MemoziTheme.typography.ssuLight15
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_calendar_bottomsheet_forward),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable {
                                    val (newYear, newMonth) = CalendarUtils.showNextMonth(
                                        year,
                                        month
                                    )
                                    year = newYear
                                    month = newMonth
                                }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        WeekHeader()
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val yearMonth = YearMonth.of(year, month)
                                val firstDayOfMonth = yearMonth.atDay(1)
                                val daysInMonth = yearMonth.lengthOfMonth()
                                val firstDayOfWeek = firstDayOfMonth.dayOfWeek

                                val dayOfWeekIndex = firstDayOfWeek.value % 7

                                val calendarDays = mutableListOf<LocalDate>()
                                for (i in 1..daysInMonth) {
                                    calendarDays.add(LocalDate.of(year, month, i))
                                }

                                val leadingEmptyDays: List<LocalDate?> =
                                    List(dayOfWeekIndex) { null }

                                val trailingEmptyDays: List<LocalDate?> =
                                    List((7 - (calendarDays.size + leadingEmptyDays.size) % 7) % 7) { null }

                                val fullCalendarDays: List<LocalDate?> =
                                    leadingEmptyDays + calendarDays + trailingEmptyDays

                                val rows = fullCalendarDays.chunked(7) // 일주일씩 끊어서 배열 생성
                                rows.forEach { week ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        week.forEach { date ->
                                            Box(
                                                modifier = Modifier
                                                    .height((LocalConfiguration.current.screenHeightDp * 0.046f).dp)
                                                    .width(((LocalConfiguration.current.screenWidthDp - 40) / 7).dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                // null인 경우는 아무것도 출력하지 않음
                                                date?.let {
                                                    val hasDiaryEntry =
                                                        diaryState.diaryList.any { diary ->
                                                            LocalDate.parse(diary.createdAt) == it
                                                        }
                                                    if (hasDiaryEntry) {
                                                        Box(
                                                            modifier = Modifier
                                                                .size(20.dp)
                                                                .background(
                                                                    color = MemoziTheme.colors.mainPurple,
                                                                    shape = CircleShape
                                                                )
                                                                .align(Alignment.Center)
                                                        ) {
                                                            Text(
                                                                text = it.dayOfMonth.toString(),
                                                                modifier = Modifier.align(Alignment.Center),
                                                                color = MemoziTheme.colors.white,
                                                                style = MemoziTheme.typography.ngReg12_140
                                                            )
                                                        }
                                                    } else {
                                                        Text(
                                                            text = it.dayOfMonth.toString(),
                                                            modifier = Modifier.align(Alignment.Center),
                                                            color = MemoziTheme.colors.black,
                                                            style = MemoziTheme.typography.ngReg12_140
                                                        )
                                                        if (
                                                            date.year == LocalDate.now().year &&
                                                            date.monthValue == LocalDate.now().monthValue &&
                                                            date.dayOfMonth == LocalDate.now().dayOfMonth
                                                        ) {
                                                            Image(
                                                                painter = painterResource(id = R.drawable.ic_calendar_today),
                                                                contentDescription = null,
                                                                modifier = Modifier.align(Alignment.Center)
                                                            )
                                                            Text(
                                                                text = "TODAY",
                                                                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                                                                color = MemoziTheme.colors.black,
                                                                style = MemoziTheme.typography.ngBold7
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            sheetContainerColor = MemoziTheme.colors.white,
            sheetShadowElevation = 16.dp,
            sheetDragHandle = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width((LocalConfiguration.current.screenWidthDp * 0.11f).dp)
                        .height(4.dp)
                        .background(
                            color = MemoziTheme.colors.gray02,
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            },
            sheetPeekHeight = height
        ) {}
    }
}

@Composable
fun DiaryFeedTopAppBar(
    navigateToMemo: () -> Unit,
    navigateToSetting: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "MEMO : Zi",
            style = MemoziTheme.typography.appnameBold15
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "메모",
            modifier = Modifier.clickable { navigateToMemo() },
            style = MemoziTheme.typography.ssuLight13
        )
        Image(
            painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_black),
            contentDescription = null
        )
        Text(
            text = "설정",
            modifier = Modifier.clickable { navigateToSetting() },
            style = MemoziTheme.typography.ssuLight13
        )
    }
}

@Composable
fun DiaryFeedGreeting(
    userName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MemoziTheme.colors.white,
                        fontSize = MemoziTheme.typography.ngBold15.fontSize,
                        fontWeight = MemoziTheme.typography.ngBold15.fontWeight
                    )
                ) {
                    append(userName)
                }
                withStyle(
                    style = SpanStyle(
                        color = MemoziTheme.colors.white,
                        fontSize = MemoziTheme.typography.ngReg13.fontSize,
                        fontWeight = MemoziTheme.typography.ngReg13.fontWeight
                    )
                ) {
                    append(" 님, 좋은 저녁이에요! 오늘 하루는 어떠셨나요?")
                }
            }
        )
    }
}

@Composable
fun SelectDisplayType(
    listSelectedState: Boolean,
    onChangedListSelectedState: (Boolean) -> Unit,
    calendarSelectedState: Boolean,
    onChangedCalendarSelectedState: (Boolean) -> Unit,
    onChangedCalendarBottomSheetState: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter =
            if (listSelectedState) {
                painterResource(id = R.drawable.ic_diary_feed_list_selected)
            } else {
                painterResource(id = R.drawable.ic_diary_feed_list_not_selected)
            },
            contentDescription = null,
            modifier = Modifier.clickable {
                onChangedListSelectedState(true)
                onChangedCalendarSelectedState(false)
                onChangedCalendarBottomSheetState(false)
            }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_diary_feed_vertical_line_white),
            contentDescription = null
        )
        Image(
            painter =
            if (calendarSelectedState) {
                painterResource(id = R.drawable.ic_diary_feed_calendar_selected)
            } else {
                painterResource(id = R.drawable.ic_diary_feed_calendar_not_selected)
            },
            contentDescription = null,
            modifier = Modifier.clickable {
                onChangedListSelectedState(false)
                onChangedCalendarSelectedState(true)
                onChangedCalendarBottomSheetState(true)
            }
        )
    }
}

@Composable
fun DiaryFeedWriteCard(
    diaryWriteState: Boolean,
    onChangeDiaryWriteState: (Boolean) -> Unit,
    maxDiaryLength: Int,
    diaryContent: String,
    onChangedDiaryContent: (String) -> Unit,
    onChangedLocationDialogState: (Boolean) -> Unit,
    location: String = "",
    isLocationExist: Boolean = false,
    isDiaryAvailable: Boolean,
    onChangedDiaryWritten: (Boolean) -> Unit,
    postDiary: (String) -> Unit,
    updateUrl: (Uri?) -> Unit,
    imageURL: String? = null
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            updateUrl(uri)
        }
    }
    val uriState = remember { mutableStateOf<Uri?>(null) }

    val takePicture =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result) {
                updateUrl(uriState.value)
            }
        }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = MemoziTheme.colors.white
            )
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(MemoziTheme.colors.white),
        content = {
            if (!diaryWriteState) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_feed_plus),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable { onChangeDiaryWriteState(true) }
                    )
                    Text(
                        text = "오늘은 일기를 적지 않으셨어요 !\n 오늘의 기억을 적어주세요 :)",
                        style = MemoziTheme.typography.ssuLight13
                    )
                }
            }
            if (diaryWriteState) {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 8.dp)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (imageURL != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageURL)
                                .crossfade(true)
                                .build(),
                            contentDescription = "일기사진",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth(0.26f)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(14.dp))
                        )
                    }
                    if (isLocationExist) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_diary_feed_pin_small),
                                contentDescription = null,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = location,
                                color = MemoziTheme.colors.black,
                                style = MemoziTheme.typography.ngReg8
                            )
                        }
                    }
                    BasicTextField(
                        value = diaryContent,
                        onValueChange = { newContent ->
                            if (newContent.length <= 100) {
                                onChangedDiaryContent(newContent)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MemoziTheme.typography.ngReg12_140.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.align(Alignment.Start),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        DiaryFeedWriteOption(
                            id = R.drawable.ic_diary_feed_camera,
                            onClick = {
                                val photoUri = createImageUri(context)
                                uriState.value = photoUri
                                if (photoUri != null) takePicture.launch(photoUri)
                            }
                        )
                        DiaryFeedWriteOption(
                            id = R.drawable.ic_diary_feed_gallery,
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                                ).apply {
                                    type = "image/*"
                                }
                                launcher.launch(intent)
                            }
                        )
                        DiaryFeedWriteOption(
                            id = R.drawable.ic_diary_feed_pin,
                            onClick = { onChangedLocationDialogState(true) }
                        )
//                        DiaryFeedWriteOption(
//                            id = R.drawable.ic_diary_feed_random,
//                            onClick = {}
//                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(brush = MemoziTheme.colors.gradientBrush)
                                ) {
                                    append("${maxDiaryLength - diaryContent.length}")
                                }
                            },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .align(Alignment.Bottom)
                        )
                        Button(
                            onClick = {
                                onChangedDiaryWritten(true) /* 일기 작성 완료 */
                                postDiary(diaryContent)
                            },
                            modifier = Modifier
                                .width(68.dp)
                                .height(34.dp)
                                .align(Alignment.CenterVertically),
                            enabled = isDiaryAvailable,
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonColors(
                                contentColor = MemoziTheme.colors.white,
                                containerColor = MemoziTheme.colors.mainPurple,
                                disabledContentColor = MemoziTheme.colors.white,
                                disabledContainerColor = MemoziTheme.colors.gray02
                            ),
                            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "등록",
                                modifier = Modifier.padding(0.dp),
                                style = MemoziTheme.typography.ssuLight12
                            )
                        }
                    }
                }
            }
        }
    )
}

fun createImageUri(context: Context): Uri? {
    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "new_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Memozi") // 원하는 경로 설정
    }
    return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
}

@Composable
fun DiaryFeedWriteOption(
    id: Int,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier.customClickable {
            onClick()
        }
    )
}

@Composable
fun DiaryFeedDisplayCard(
    diary: List<Diary>,
    editEvent: () -> Unit = {},
    deleteEvent: (Int) -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                shape = RoundedCornerShape(12.dp),
                color = MemoziTheme.colors.white
            )
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(MemoziTheme.colors.white),
        content = {
            if (diary.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(brush = MemoziTheme.colors.gradientBrush)
                            ) {
                                append("Diary Of Nov.")
                            }
                        },
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MemoziTheme.typography.appnameBold13
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(1.dp)
                            .background(MemoziTheme.colors.gray02)
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(diary) {
                            DailyDiaryItem(
                                year = it.createdAt.substring(0, 4).toInt(),
                                month = it.createdAt.substring(5, 7).toInt(),
                                day = it.createdAt.substring(8, 10).toInt(),
                                location = it.location,
                                dayOfWeek = it.dayOfWeek,
                                diaryContent = it.content,
                                imageUrl = if (it.images.isNotEmpty()) it.images[0] else null,
                                deleteEvent = { deleteEvent(it.diaryId) }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DailyDiaryItem(
    year: Int,
    month: Int,
    day: Int,
    dayOfWeek: String,
    diaryContent: String,
    imageUrl: String? = null,
    location: String? = null,
    editEvent: () -> Unit = {},
    deleteEvent: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            DropDownMenu(
                modifier = Modifier.padding(end = 10.dp),
                topPaddingValues = PaddingValues(0.dp),
                onEditClick = { editEvent() },
                onDeleteClick = { deleteEvent() },
                onlyDelete = true
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${year}년 ${month}월 ${day}일 | $dayOfWeek",
                modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
                style = MemoziTheme.typography.ssuLight12
            )
            if (imageUrl != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "카테고리",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(vertical = 7.dp)
                )
            }
            if (location != null) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_feed_pin_small),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = location,
                        style = MemoziTheme.typography.ngReg8
                    )
                }
            }
            Text(
                text = diaryContent,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MemoziTheme.typography.ngReg12_140
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MemoziTheme.colors.gray02)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDiaryFeedScreen() {
    MemoziTheme {
    }
}
