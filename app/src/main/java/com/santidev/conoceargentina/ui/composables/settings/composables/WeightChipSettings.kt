package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val AppBackground = Color(0xFFEEE6D7)
private val CardBackground = Color(0xFFEEE4D2)
private val AccentBlue = Color(0xFF1A3A5C)
private val AccentBlueLight = Color(0xFF2D5F8A)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF666666)

@Composable
fun WeightChipSettings(label: String, weight: Int, currentWeight: Int, onClick: () -> Unit) {
  val selected = weight == currentWeight
  FilterChip(
    selected = selected,
    onClick = onClick,
    label = { Text(label, fontWeight = FontWeight(weight), fontSize = 14.sp) },
    colors = FilterChipDefaults.filterChipColors(
      selectedContainerColor = AccentBlue,
      selectedLabelColor = Color.White,
      containerColor = CardBackground,
      labelColor = TextPrimary
    ),
    border = FilterChipDefaults.filterChipBorder(
      enabled = true,
      selected = selected,
      borderColor = AccentBlue.copy(alpha = 0.4f),
      selectedBorderColor = AccentBlue
    )
  )
}