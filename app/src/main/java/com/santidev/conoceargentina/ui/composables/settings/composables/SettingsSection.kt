package com.santidev.conoceargentina.ui.composables.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSection(title: String, content: @Composable () -> Unit) {
  Column(modifier = Modifier.padding(bottom = 24.dp)) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(bottom = 12.dp)
    )
    content()
  }
}