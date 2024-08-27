package com.memozi.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class MemoziColors(
    // Main Colors
    mainPink: Color,
    mainPurple: Color,
    mainPurple02: Color,

    // Sub Colors
    purple: Color,
    pink: Color,
    blue01: Color,
    blue02: Color,
    yellowGreen01: Color,
    yellowGreen02: Color,
    yellow01: Color,
    yellow02: Color,
    orange: Color,

    // Black & White
    black: Color,
    white: Color,
    loginBlack: Color,
    black20: Color,

    // Grayscale
    gray01: Color,
    gray02: Color,
    gray03: Color,
    gray04: Color,
    gray05: Color,
    gray06: Color,
    gray07: Color,

    // Caution & Delete
    cautionRed: Color,
    red: Color,

    // Kakao
    kakao: Color
) {
    var mainPink by mutableStateOf(mainPink)
        private set
    var mainPurple by mutableStateOf(mainPurple)
        private set
    var mainPurple02 by mutableStateOf(mainPurple02)
        private set

    var purple by mutableStateOf(purple)
        private set
    var pink by mutableStateOf(pink)
        private set
    var blue01 by mutableStateOf(blue01)
        private set
    var blue02 by mutableStateOf(blue02)
        private set
    var yellowGreen01 by mutableStateOf(yellowGreen01)
        private set
    var yellowGreen02 by mutableStateOf(yellowGreen02)
        private set
    var yellow01 by mutableStateOf(yellow01)
        private set
    var yellow02 by mutableStateOf(yellow02)
        private set
    var orange by mutableStateOf(orange)
        private set

    var black by mutableStateOf(black)
        private set
    var white by mutableStateOf(white)
        private set
    var loginBlack by mutableStateOf(loginBlack)
        private set
    var black20 by mutableStateOf(black20)
        private set

    var gray01 by mutableStateOf(gray01)
        private set
    var gray02 by mutableStateOf(gray02)
        private set
    var gray03 by mutableStateOf(gray03)
        private set
    var gray04 by mutableStateOf(gray04)
        private set
    var gray05 by mutableStateOf(gray05)
        private set
    var gray06 by mutableStateOf(gray06)
        private set
    var gray07 by mutableStateOf(gray07)
        private set

    var cautionRed by mutableStateOf(cautionRed)
        private set
    var red by mutableStateOf(red)
        private set

    var kakao by mutableStateOf(kakao)
        private set

    fun copy(): MemoziColors = MemoziColors(
        mainPink, mainPurple, mainPurple02,
        purple, pink, blue01, blue02, yellowGreen01, yellowGreen02, yellow01, yellow02, orange,
        black, white, loginBlack, black20,
        gray01, gray02, gray03, gray04, gray05, gray06, gray07,
        cautionRed, red,
        kakao
    )

    fun update(other: MemoziColors) {
        mainPink = other.mainPink
        mainPurple = other.mainPurple
        mainPurple02 = other.mainPurple02
        purple = other.purple
        pink = other.pink
        blue01 = other.blue01
        blue02 = other.blue02
        yellowGreen01 = other.yellowGreen01
        yellowGreen02 = other.yellowGreen02
        yellow01 = other.yellow01
        yellow02 = other.yellow02
        orange = other.orange
        black = other.black
        white = other.white
        loginBlack = other.loginBlack
        black20 = other.black20
        gray01 = other.gray01
        gray02 = other.gray02
        gray03 = other.gray03
        gray04 = other.gray04
        gray05 = other.gray05
        gray06 = other.gray06
        gray07 = other.gray07
        cautionRed = other.cautionRed
        red = other.red
        kakao = other.kakao
    }
}

fun MemoziDarkColor(
    // Main Colors
    mainPink: Color = MainPink,
    mainPurple: Color = MainPurple,
    mainPurple02: Color = MainPurple02,

    // Sub Colors
    purple: Color = Purple,
    pink: Color = Pink,
    blue01: Color = Blue01,
    blue02: Color = Blue02,
    yellowGreen01: Color = YellowGreen01,
    yellowGreen02: Color = YellowGreen02,
    yellow01: Color = Yellow01,
    yellow02: Color = Yellow02,
    orange: Color = Orange,

    // Black & White
    black: Color = Black,
    white: Color = White,
    loginBlack: Color = LoginBlack,
    black20: Color = Black20,

    // Grayscale
    gray01: Color = Gray01,
    gray02: Color = Gray02,
    gray03: Color = Gray03,
    gray04: Color = Gray04,
    gray05: Color = Gray05,
    gray06: Color = Gray06,
    gray07: Color = Gray07,

    // Caution & Delete
    cautionRed: Color = CautionRed,
    red: Color = Red,

    // Kakao
    kakao: Color = Kakao
) = MemoziColors(
    mainPink, mainPurple, mainPurple02,
    purple, pink, blue01, blue02, yellowGreen01, yellowGreen02, yellow01, yellow02, orange,
    black, white, loginBlack, black20,
    gray01, gray02, gray03, gray04, gray05, gray06, gray07,
    cautionRed, red,
    kakao
)

private val LocalMemoziColors =
    staticCompositionLocalOf<MemoziColors> { error("No MemoziColors provided") }
private val LocalMemoziTypography =
    staticCompositionLocalOf<MemoziTypography> { error("No MemoziTypography provided") }

object MemoziTheme {
    val colors: MemoziColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMemoziColors.current

    val typography: MemoziTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMemoziTypography.current
}

@Composable
fun provideMemoziColorsAndTypography(
    colors: MemoziColors,
    typography: MemoziTypography,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors.copy() }
    provideColors.update(colors)
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)

    CompositionLocalProvider(
        LocalMemoziColors provides provideColors,
        LocalMemoziTypography provides provideTypography,
        content = content
    )
}

@Composable
fun MemoziTheme(content: @Composable () -> Unit) {
    val colors = MemoziDarkColor()
    val typography = MemoziTypography()
    provideMemoziColorsAndTypography(colors, typography) {
        MaterialTheme(content = content)
    }
}
