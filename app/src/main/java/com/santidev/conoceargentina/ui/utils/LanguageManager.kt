package com.santidev.conoceargentina.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.santidev.conoceargentina.ui.data.local.ListStrings
import com.santidev.conoceargentina.ui.data.local.StringResources

// CompositionLocal para acceder a los strings en cualquier lugar
val LocalStrings = compositionLocalOf { StringResources.spanish }
val LocalListStrings = compositionLocalOf { ListStrings.spanish }

@Composable
fun LanguageProvider(
  initialLanguage: String = "ES",
  content: @Composable () -> Unit
) {
  var currentLanguage by remember { mutableStateOf(initialLanguage) }
  
  val strings = remember(currentLanguage) {
    StringResources.getStrings(currentLanguage)
  }
  val listStrings = remember(currentLanguage) {
    ListStrings.getStrings(currentLanguage)
  }
  
  CompositionLocalProvider(
    LocalStrings provides strings,
    LocalListStrings provides listStrings,
    LocalLanguage provides currentLanguage,
    LocalLanguageChanger provides { newLang -> currentLanguage = newLang }
  ) {
    content()
  }
}

val LocalLanguage = compositionLocalOf { "ES" }
val LocalLanguageChanger = compositionLocalOf<(String) -> Unit> { {} }