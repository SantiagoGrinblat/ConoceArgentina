package com.santidev.conoceargentina.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatLineSpacing
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.composables.settings.AccessibilityViewModel
import com.santidev.conoceargentina.ui.composables.settings.composables.AlignChipSettings
import com.santidev.conoceargentina.ui.composables.settings.composables.SectionHeaderSettings
import com.santidev.conoceargentina.ui.composables.settings.composables.SettingsSection
import com.santidev.conoceargentina.ui.composables.settings.composables.SliderSetting
import com.santidev.conoceargentina.ui.composables.settings.composables.WeightChipSettings

private val AppBackground = Color(0xFFEEE6D7)
private val CardBackground = Color(0xFFEEE4D2)
private val AccentBlue = Color(0xFF1A3A5C)
private val AccentBlueLight = Color(0xFF2D5F8A)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF666666)

@Composable
fun AccessibilitySettingsScreen(
  viewModel: AccessibilityViewModel = viewModel(),
  onNavigate: (Routes) -> Unit,
) {
  
  val settings by viewModel.settings.collectAsStateWithLifecycle()
  
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(AppBackground)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
      // TÍTULO
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Accessibility,
          contentDescription = null,
          tint = AccentBlue,
          modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = "Accesibilidad",
          fontSize = 26.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      }
      Text(
        text = "Personalizá la experiencia de lectura a tu gusto",
        fontSize = 16.sp,
        color = TextSecondary,
        modifier = Modifier.padding(bottom = 28.dp)
      )
      
      // SECCIÓN VISUALIZACIÓN
      SectionHeaderSettings(title = "Visualización", icon = Icons.Default.RemoveRedEye)
      
      // TAMAÑO DE FUENTE
      SettingsSection(
        title = "Escala de fuente",
        description = "Ajusta el tamaño del texto en toda la app",
        icon = Icons.Default.FormatSize
      ) {
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
      
      // GROSOR DE FUENTE
      SettingsSection(
        title = "Grosor de fuente",
        description = "Cambia el peso visual del texto",
        icon = Icons.Default.FormatBold
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          WeightChipSettings("Normal", 400, settings.fontWeight) { viewModel.updateFontWeight(400) }
          WeightChipSettings("Medio", 500, settings.fontWeight) { viewModel.updateFontWeight(500) }
          WeightChipSettings(
            "Negrita",
            700,
            settings.fontWeight
          ) { viewModel.updateFontWeight(700) }
        }
      }
      
      // ESPACIADO ENTRE LETRAS
      SettingsSection(
        title = "Espaciado entre letras",
        description = "Separa los caracteres para mayor legibilidad",
        icon = Icons.Default.SpaceBar
      ) {
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
      
      // ALTURA DE LÍNEA
      SettingsSection(
        title = "Altura de línea",
        description = "Aumenta el espacio entre renglones",
        icon = Icons.Default.FormatLineSpacing
      ) {
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
      
      // SECCIÓN TEXTO
      SectionHeaderSettings(title = "Texto", icon = Icons.Default.Title)
      
      // ALINEACIÓN
      SettingsSection(
        title = "Alineación",
        description = "Posición horizontal del texto",
        icon = Icons.AutoMirrored.Filled.FormatAlignLeft
      ) {
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
          AlignChipSettings(
            "Derecha",
            "END",
            settings.textAlign
          ) { viewModel.updateTextAlign("END") }
        }
      }
      
      // SECCIÓN CONTENIDO
      SectionHeaderSettings(title = "Contenido", icon = Icons.Default.Image)
      
      // IMÁGENES
      SettingsSection(
        title = "Imágenes",
        description = "Mostrá u ocultá las fotos de las provincias",
        icon = Icons.Default.HideImage
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = if (settings.showImages) "Imágenes visibles" else "Imágenes ocultas",
            fontSize = 14.sp,
            color = TextSecondary
          )
          Switch(
            checked = settings.showImages,
            onCheckedChange = { viewModel.toggleImages(it) },
            colors = SwitchDefaults.colors(
              checkedThumbColor = Color.White,
              checkedTrackColor = AccentBlue,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = Color(0xFFBBBBBB)
            )
          )
        }
      }
      
      // BOTÓN RESETEAR
      Spacer(modifier = Modifier.height(8.dp))
      OutlinedButton(
        onClick = {
          viewModel.updateFontSize(1f)
          viewModel.updateFontWeight(400)
          viewModel.updateLetterSpacing(0.5f)
          viewModel.updateLineHeight(1f)
          viewModel.updateTextAlign("START")
          viewModel.toggleImages(true)
        },
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.5.dp, AccentBlue),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(
          imageVector = Icons.Default.RestartAlt,
          contentDescription = null,
          tint = AccentBlue,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Restablecer valores por defecto",
          color = AccentBlue,
          fontWeight = FontWeight.Medium
        )
      }
    }
  }
}