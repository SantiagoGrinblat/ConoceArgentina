package com.santidev.conoceargentina.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.settings.AccessibilitySettingsItems

@Composable
fun AppTypography(settings: AccessibilitySettingsItems): Typography {
  
  val fontFamily = FontFamily.Default

//  val fontFamily = if (settings.useReadableFont) {
//    FontFamily(Font(R.font.open_dyslexic)) // lo agregamos después
//  } else {
//    FontFamily.Default
//  }
  
  val textAlign = when (settings.textAlign) {
    "CENTER" -> TextAlign.Center
    "END" -> TextAlign.End
    else -> TextAlign.Start
  }
  
  val baseStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight(settings.fontWeight),
    letterSpacing = settings.letterSpacing.sp,
    textAlign = textAlign
  )
  
  return Typography(
    bodyLarge = baseStyle.copy(
      fontSize = (16 * settings.fontSizeMultiplier).sp,
      lineHeight = (24 * settings.lineHeightMultiplier).sp,
    ),
    bodyMedium = baseStyle.copy(
      fontSize = (14 * settings.fontSizeMultiplier).sp,
      lineHeight = (20 * settings.lineHeightMultiplier).sp,
    ),
    bodySmall = baseStyle.copy(
      fontSize = (12 * settings.fontSizeMultiplier).sp,
      lineHeight = (16 * settings.lineHeightMultiplier).sp,
    ),
    titleLarge = baseStyle.copy(
      fontSize = (22 * settings.fontSizeMultiplier).sp,
      lineHeight = (28 * settings.lineHeightMultiplier).sp,
      fontWeight = FontWeight.Bold
    ),
    titleMedium = baseStyle.copy(
      fontSize = (18 * settings.fontSizeMultiplier).sp,
      lineHeight = (24 * settings.lineHeightMultiplier).sp,
      fontWeight = FontWeight.SemiBold
    ),
    labelSmall = baseStyle.copy(
      fontSize = (11 * settings.fontSizeMultiplier).sp,
      lineHeight = (16 * settings.lineHeightMultiplier).sp,
    )
  )
}