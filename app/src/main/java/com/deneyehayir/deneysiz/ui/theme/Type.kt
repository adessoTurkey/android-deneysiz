package com.deneyehayir.deneysiz.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.deneyehayir.deneysiz.R

private val Mulish = FontFamily(
    Font(R.font.mulish_regular, FontWeight.Normal),
    Font(R.font.mulish_medium, FontWeight.Medium),
    Font(R.font.mulish_semibold, FontWeight.SemiBold),
    Font(R.font.mulish_bold, FontWeight.Bold)
)

val Typography = Typography(
    defaultFontFamily = Mulish
)
