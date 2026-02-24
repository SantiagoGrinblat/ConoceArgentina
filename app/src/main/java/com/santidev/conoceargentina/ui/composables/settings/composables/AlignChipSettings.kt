package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlignChipSettings(label: String, align: String, currentAlign: String, onClick: () -> Unit) {
  val selected = align == currentAlign
  FilterChip(
    selected = selected,
    onClick = onClick,
    label = { Text(label) }
  )
}