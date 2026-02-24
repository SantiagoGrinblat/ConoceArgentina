package com.santidev.conoceargentina.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.composables.settings.AccessibilityViewModel
import com.santidev.conoceargentina.ui.composables.settings.composables.AlignChipSettings
import com.santidev.conoceargentina.ui.composables.settings.composables.SettingsSection
import com.santidev.conoceargentina.ui.composables.settings.composables.SliderSetting
import com.santidev.conoceargentina.ui.composables.settings.composables.WeightChipSettings

@Composable
fun AccessibilitySettingsScreen(
  viewModel: AccessibilityViewModel = viewModel(),
  onNavigate: (Routes) -> Unit,
) {
  
  val settings by viewModel.settings.collectAsStateWithLifecycle()
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(16.dp)
  ) {
    Text(
      text = "Accesibilidad",
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(bottom = 24.dp)
    )
    
    // --- TAMAÑO DE FUENTE ---
    SettingsSection(title = "Tamaño de fuente") {
      SliderSetting(
        value = settings.fontSizeMultiplier,
        onValueChange = { viewModel.updateFontSize(it) },
        valueRange = 0.8f..1.5f,
        steps = 6,
        label = when {
          settings.fontSizeMultiplier <= 0.85f -> "Pequeño"
          settings.fontSizeMultiplier <= 1.05f -> "Normal"
          settings.fontSizeMultiplier <= 1.25f -> "Grande"
          else -> "Muy grande"
        }
      )
    }
    
    // --- GROSOR DE FUENTE ---
    SettingsSection(title = "Grosor de fuente") {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        WeightChipSettings("Normal", 400, settings.fontWeight) { viewModel.updateFontWeight(400) }
        WeightChipSettings("Medio", 500, settings.fontWeight) { viewModel.updateFontWeight(500) }
        WeightChipSettings("Negrita", 700, settings.fontWeight) { viewModel.updateFontWeight(700) }
      }
    }
    
    // --- ESPACIADO ENTRE LETRAS ---
    SettingsSection(title = "Espaciado entre letras") {
      SliderSetting(
        value = settings.letterSpacing,
        onValueChange = { viewModel.updateLetterSpacing(it) },
        valueRange = 0f..3f,
        steps = 5,
        label = when {
          settings.letterSpacing <= 0.5f -> "Normal"
          settings.letterSpacing <= 1.5f -> "Amplio"
          else -> "Muy amplio"
        }
      )
    }
    
    // --- ALTURA DE LÍNEA ---
    SettingsSection(title = "Altura de línea") {
      SliderSetting(
        value = settings.lineHeightMultiplier,
        onValueChange = { viewModel.updateLineHeight(it) },
        valueRange = 1f..2f,
        steps = 4,
        label = when {
          settings.lineHeightMultiplier <= 1.1f -> "Normal"
          settings.lineHeightMultiplier <= 1.5f -> "Amplio"
          else -> "Muy amplio"
        }
      )
    }
    
    // --- ALINEACIÓN ---
    SettingsSection(title = "Alineación del texto") {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        AlignChipSettings(
          "Izquierda",
          "START",
          settings.textAlign
        ) { viewModel.updateTextAlign("START") }
        AlignChipSettings(
          "Centro",
          "CENTER",
          settings.textAlign
        ) { viewModel.updateTextAlign("CENTER") }
        AlignChipSettings("Derecha", "END", settings.textAlign) { viewModel.updateTextAlign("END") }
      }
    }
    
    // --- MOSTRAR IMÁGENES ---
    
    SettingsSection(title = "Imágenes") {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = if (settings.showImages) "Imágenes visibles" else "Imágenes ocultas",
          style = MaterialTheme.typography.bodyMedium
        )
        Switch(
          checked = settings.showImages,
          onCheckedChange = { viewModel.toggleImages(it) }
        )
      }
    }
    
    // --- BOTÓN RESETEAR ---
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedButton(
      onClick = {
        viewModel.updateFontSize(1f)
        viewModel.updateFontWeight(400)
        viewModel.updateLetterSpacing(0.5f)
        viewModel.updateLineHeight(1f)
        viewModel.updateTextAlign("START")
        viewModel.toggleImages(true)
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Restablecer valores por defecto")
    }
  }
}