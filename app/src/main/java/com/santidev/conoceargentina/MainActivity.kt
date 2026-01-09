package com.santidev.conoceargentina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.santidev.conoceargentina.navigation.core.NavWrapper
import com.santidev.conoceargentina.ui.screens.HomeScreen
import com.santidev.conoceargentina.ui.theme.ConoceArgentinaTheme
import com.santidev.conoceargentina.ui.utils.LanguageProvider

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ConoceArgentinaTheme {
        LanguageProvider {
          NavWrapper()
        }
      }
    }
  }
}