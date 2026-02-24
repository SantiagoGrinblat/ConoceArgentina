package com.santidev.conoceargentina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santidev.conoceargentina.navigation.core.NavWrapper
import com.santidev.conoceargentina.ui.composables.settings.AccessibilityViewModel
import com.santidev.conoceargentina.ui.composables.settings.LocalAccessibilitySettings
import com.santidev.conoceargentina.ui.theme.ConoceArgentinaTheme
import com.santidev.conoceargentina.ui.utils.LanguageProvider

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val accessibilityViewModel: AccessibilityViewModel by viewModels()
    setContent {
      val settings by accessibilityViewModel.settings.collectAsStateWithLifecycle()
      CompositionLocalProvider(LocalAccessibilitySettings provides settings) {
        ConoceArgentinaTheme {
          LanguageProvider {
            NavWrapper()
          }
        }
      }
    }
  }
}