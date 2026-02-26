package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val AppBackground = Color(0xFFEEE6D7)
private val CardBackground = Color(0xFFEEE4D2)
private val AccentBlue = Color(0xFF1A3A5C)
private val AccentBlueLight = Color(0xFF2D5F8A)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF666666)

@Composable
fun SliderSetting(
  value: Float,
  onValueChange: (Float) -> Unit,
  valueRange: ClosedFloatingPointRange<Float>,
  steps: Int,
  label: String
) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
    Slider(
      value = value,
      onValueChange = onValueChange,
      valueRange = valueRange,
      steps = steps,
      modifier = Modifier.fillMaxWidth(),
      colors = SliderDefaults.colors(
        thumbColor = AccentBlue,
        activeTrackColor = AccentBlue,
        inactiveTrackColor = AccentBlue.copy(alpha = 0.2f)
      )
    )
  }
}