package com.santidev.conoceargentina.ui.composables.settings

data class AccessibilitySettingsItems(
  val fontSizeMultiplier: Float = 1f,      // 0.8f = chico, 1f = normal, 1.3f = grande
  val fontWeight: Int = 400,               // 400 = Normal, 700 = Bold
  val letterSpacing: Float = 0.5f,         // espaciado entre letras en sp
  val lineHeightMultiplier: Float = 1f,    // multiplicador de altura de línea
  val textAlign: String = "START",         // START, CENTER, END
  val showImages: Boolean = true,          // mostrar u ocultar imágenes
  val useReadableFont: Boolean = false     // fuente alternativa más legible
)
