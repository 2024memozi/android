package com.memozi.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// gyeonggibattang
val AppName = FontFamily(Font(R.font.gyeonggibattang_bold, FontWeight.Bold))

// Ssurround air
val Ssurround = FontFamily(Font(R.font.ssurround_air, FontWeight.Light))

// Nanum Gothic
val Nanum = FontFamily(Font(R.font.nanumgothic, FontWeight.Normal))
val NanumExtraBold = FontFamily(Font(R.font.nanumgothic_extrabold, FontWeight.ExtraBold))
val NanumBold = FontFamily(Font(R.font.nanumgothic_bold, FontWeight.Bold))
val NanumLight = FontFamily(Font(R.font.nanumgothic_light, FontWeight.Light))

@Stable
class MemoziTypography internal constructor(
    appnameBold24: TextStyle,
    appnameBold15: TextStyle,
    appnameBold13: TextStyle,
    ssuLight19: TextStyle,
    ssuLight16: TextStyle,
    ssuLight15: TextStyle,
    ssuLight13: TextStyle,
    ssuLight12: TextStyle,
    ssuLight11: TextStyle,
    ngBold21: TextStyle,
    ngBold15: TextStyle,
    ngBold14: TextStyle,
    ngBold12_170: TextStyle,
    ngBold12_140: TextStyle,
    ngBold11: TextStyle,
    ngBold7: TextStyle,
    ngReg15: TextStyle,
    ngReg14: TextStyle,
    ngReg13: TextStyle,
    ngReg12_170: TextStyle,
    ngReg12_140: TextStyle,
    ngReg11: TextStyle,
    ngReg8: TextStyle,
    ngLight8: TextStyle
) {
    var appnameBold24: TextStyle by mutableStateOf(appnameBold24)
        private set
    var appnameBold15: TextStyle by mutableStateOf(appnameBold15)
        private set
    var appnameBold13: TextStyle by mutableStateOf(appnameBold13)
        private set
    var ssuLight19: TextStyle by mutableStateOf(ssuLight19)
        private set
    var ssuLight16: TextStyle by mutableStateOf(ssuLight16)
        private set
    var ssuLight15: TextStyle by mutableStateOf(ssuLight15)
        private set
    var ssuLight13: TextStyle by mutableStateOf(ssuLight13)
        private set
    var ssuLight12: TextStyle by mutableStateOf(ssuLight12)
        private set
    var ssuLight11: TextStyle by mutableStateOf(ssuLight11)
        private set
    var ngBold21: TextStyle by mutableStateOf(ngBold21)
        private set
    var ngBold15: TextStyle by mutableStateOf(ngBold15)
        private set
    var ngBold14: TextStyle by mutableStateOf(ngBold14)
        private set
    var ngBold12_170: TextStyle by mutableStateOf(ngBold12_170)
        private set
    var ngBold12_140: TextStyle by mutableStateOf(ngBold12_140)
        private set
    var ngBold11: TextStyle by mutableStateOf(ngBold11)
        private set
    var ngBold7: TextStyle by mutableStateOf(ngBold7)
        private set
    var ngReg15: TextStyle by mutableStateOf(ngReg15)
        private set
    var ngReg14: TextStyle by mutableStateOf(ngReg14)
        private set
    var ngReg13: TextStyle by mutableStateOf(ngReg13)
        private set
    var ngReg12_170: TextStyle by mutableStateOf(ngReg12_170)
        private set
    var ngReg12_140: TextStyle by mutableStateOf(ngReg12_140)
        private set
    var ngReg11: TextStyle by mutableStateOf(ngReg11)
        private set
    var ngReg8: TextStyle by mutableStateOf(ngReg8)
        private set
    var ngLight8: TextStyle by mutableStateOf(ngLight8)
        private set

    fun copy(
        appnameBold24: TextStyle = this.appnameBold24,
        appnameBold15: TextStyle = this.appnameBold15,
        appnameBold13: TextStyle = this.appnameBold13,
        ssuLight19: TextStyle = this.ssuLight19,
        ssuLight16: TextStyle = this.ssuLight16,
        ssuLight15: TextStyle = this.ssuLight15,
        ssuLight13: TextStyle = this.ssuLight13,
        ssuLight12: TextStyle = this.ssuLight12,
        ssuLight11: TextStyle = this.ssuLight11,
        ngBold21: TextStyle = this.ngBold21,
        ngBold15: TextStyle = this.ngBold15,
        ngBold14: TextStyle = this.ngBold14,
        ngBold12_170: TextStyle = this.ngBold12_170,
        ngBold12_140: TextStyle = this.ngBold12_140,
        ngBold11: TextStyle = this.ngBold11,
        ngBold7: TextStyle = this.ngBold7,
        ngReg15: TextStyle = this.ngReg15,
        ngReg14: TextStyle = this.ngReg14,
        ngReg13: TextStyle = this.ngReg13,
        ngReg12_170: TextStyle = this.ngReg12_170,
        ngReg12_140: TextStyle = this.ngReg12_140,
        ngReg11: TextStyle = this.ngReg11,
        ngReg8: TextStyle = this.ngReg8,
        ngLight8: TextStyle = this.ngLight8
    ): MemoziTypography = MemoziTypography(
        appnameBold24,
        appnameBold15,
        appnameBold13,
        ssuLight19,
        ssuLight16,
        ssuLight15,
        ssuLight13,
        ssuLight12,
        ssuLight11,
        ngBold21,
        ngBold15,
        ngBold14,
        ngBold12_170,
        ngBold12_140,
        ngBold11,
        ngBold7,
        ngReg15,
        ngReg14,
        ngReg13,
        ngReg12_170,
        ngReg12_140,
        ngReg11,
        ngReg8,
        ngLight8
    )

    fun update(other: MemoziTypography) {
        appnameBold24 = other.appnameBold24
        appnameBold15 = other.appnameBold15
        appnameBold13 = other.appnameBold13
        ssuLight19 = other.ssuLight19
        ssuLight16 = other.ssuLight16
        ssuLight15 = other.ssuLight15
        ssuLight13 = other.ssuLight13
        ssuLight12 = other.ssuLight12
        ssuLight11 = other.ssuLight11
        ngBold21 = other.ngBold21
        ngBold15 = other.ngBold15
        ngBold14 = other.ngBold14
        ngBold12_170 = other.ngBold12_170
        ngBold12_140 = other.ngBold12_140
        ngBold11 = other.ngBold11
        ngBold7 = other.ngBold7
        ngReg15 = other.ngReg15
        ngReg14 = other.ngReg14
        ngReg13 = other.ngReg13
        ngReg12_170 = other.ngReg12_170
        ngReg12_140 = other.ngReg12_140
        ngReg11 = other.ngReg11
        ngReg8 = other.ngReg8
        ngLight8 = other.ngLight8
    }
}

@Composable
fun MemoziTypography(): MemoziTypography {
    return MemoziTypography(
        appnameBold24 = TextStyle(
            fontFamily = AppName,
            fontSize = 24.sp,
            lineHeight = 24.sp, // 100%
            letterSpacing = 0.sp
        ),
        appnameBold15 = TextStyle(
            fontFamily = AppName,
            fontSize = 15.sp,
            lineHeight = 15.sp, // 100%
            letterSpacing = 0.sp
        ),
        appnameBold13 = TextStyle(
            fontFamily = AppName,
            fontSize = 13.sp,
            lineHeight = 13.sp, // 100%
            letterSpacing = 0.sp
        ),
        ssuLight19 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 19.sp,
            lineHeight = 19.sp, // 100%
            letterSpacing = 0.sp
        ),
        ssuLight16 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 16.sp,
            lineHeight = 16.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ssuLight15 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 15.sp,
            lineHeight = 15.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ssuLight13 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 13.sp,
            lineHeight = 13.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ssuLight12 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 12.sp,
            lineHeight = 12.sp, // 100%
            letterSpacing = 0.sp
        ),
        ssuLight11 = TextStyle(
            fontFamily = Ssurround,
            fontSize = 11.sp,
            lineHeight = 11.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngBold21 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 21.sp,
            lineHeight = 21.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngBold15 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 15.sp,
            lineHeight = 15.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngBold14 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 14.sp,
            lineHeight = 14.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngBold12_170 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 12.sp,
            lineHeight = 12.sp * 1.7f, // 170%
            letterSpacing = 0.sp
        ),
        ngBold12_140 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 12.sp,
            lineHeight = 12.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngBold11 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 11.sp,
            lineHeight = 11.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngBold7 = TextStyle(
            fontFamily = NanumBold,
            fontSize = 7.sp,
            lineHeight = 7.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngReg15 = TextStyle(
            fontFamily = Nanum,
            fontSize = 15.sp,
            lineHeight = 15.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngReg14 = TextStyle(
            fontFamily = Nanum,
            fontSize = 14.sp,
            lineHeight = 14.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngReg13 = TextStyle(
            fontFamily = Nanum,
            fontSize = 13.sp,
            lineHeight = 13.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngReg12_170 = TextStyle(
            fontFamily = Nanum,
            fontSize = 12.sp,
            lineHeight = 12.sp * 1.7f, // 170%
            letterSpacing = 0.sp
        ),
        ngReg12_140 = TextStyle(
            fontFamily = Nanum,
            fontSize = 12.sp,
            lineHeight = 12.sp * 1.4f, // 140%
            letterSpacing = 0.sp
        ),
        ngReg11 = TextStyle(
            fontFamily = Nanum,
            fontSize = 11.sp,
            lineHeight = 11.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngReg8 = TextStyle(
            fontFamily = Nanum,
            fontSize = 8.sp,
            lineHeight = 8.sp, // 100%
            letterSpacing = 0.sp
        ),
        ngLight8 = TextStyle(
            fontFamily = NanumLight,
            fontSize = 8.sp,
            lineHeight = 8.sp, // 100%
            letterSpacing = 0.sp
        )
    )
}
