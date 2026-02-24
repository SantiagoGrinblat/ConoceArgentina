package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeightChipSettings(label: String, weight: Int, currentWeight: Int, onClick: () -> Unit) {
  val selected = weight == currentWeight
  FilterChip(
    selected = selected,
    onClick = onClick,
    label = { Text(label, fontWeight = FontWeight(weight)) }
  )
}