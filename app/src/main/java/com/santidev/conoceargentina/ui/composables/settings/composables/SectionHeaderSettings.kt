package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AppBackground = Color(0xFFEEE6D7)
private val CardBackground = Color(0xFFEEE4D2)
private val AccentBlue = Color(0xFF1A3A5C)
private val AccentBlueLight = Color(0xFF2D5F8A)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF666666)

@Composable
fun SectionHeaderSettings(title: String, icon: ImageVector) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(bottom = 12.dp, top = 8.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = AccentBlue,
      modifier = Modifier.size(20.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = title,
      fontSize = 16.sp,
      fontWeight = FontWeight.SemiBold,
      color = AccentBlue
    )
  }
}