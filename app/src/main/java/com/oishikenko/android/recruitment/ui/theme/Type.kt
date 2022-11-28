package com.oishikenko.android.recruitment.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.oishikenko.android.recruitment.R

// Set of Material typography styles to start with

val Notosans = FontFamily(
        Font(R.font.noto_sans_jp_black, FontWeight.Black),
        Font(R.font.noto_sans_jp_light, FontWeight.Light),
        Font(R.font.noto_sans_jp_bold, FontWeight.Bold),
        Font(R.font.noto_sans_jp_thin, FontWeight.Thin),
        Font(R.font.noto_sans_jp_medium, FontWeight.Medium),
        Font(R.font.noto_sans_jp_regular, FontWeight.Normal),
)

val Typography = Typography(
        body1 = TextStyle(
                fontFamily = Notosans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
)