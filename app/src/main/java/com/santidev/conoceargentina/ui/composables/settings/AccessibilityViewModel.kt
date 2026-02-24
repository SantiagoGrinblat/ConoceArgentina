package com.santidev.conoceargentina.ui.composables.settings

import android.app.Application
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.santidev.conoceargentina.ui.utils.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AccessibilityViewModel(application: Application): AndroidViewModel(application) {
  
  private val dataStore = application.applicationContext.dataStore
  
  private val _settings = MutableStateFlow(AccessibilitySettingsItems())
  val settings: StateFlow<AccessibilitySettingsItems> = _settings.asStateFlow()
  
  init {
    viewModelScope.launch {
      dataStore.data.map { prefs ->
        AccessibilitySettingsItems(
          fontSizeMultiplier = prefs[floatPreferencesKey("fontSizeMultiplier")] ?: 1f,
          fontWeight = prefs[intPreferencesKey("fontWeight")] ?: 400,
          letterSpacing = prefs[floatPreferencesKey("letterSpacing")] ?: 0.5f,
          lineHeightMultiplier = prefs[floatPreferencesKey("lineHeightMultiplier")] ?: 1f,
          textAlign = prefs[stringPreferencesKey("textAlign")] ?: "START",
          showImages = prefs[booleanPreferencesKey("showImages")] ?: true,
          useReadableFont = prefs[booleanPreferencesKey("useReadableFont")] ?: false
        )
      }.collect { _settings.value = it }
    }
  }
  
  fun updateFontSize(multiplier: Float) = update { prefs ->
    prefs[floatPreferencesKey("fontSizeMultiplier")] = multiplier
  }
  
  fun updateFontWeight(weight: Int) = update { prefs ->
    prefs[intPreferencesKey("fontWeight")] = weight
  }
  
  fun updateLetterSpacing(spacing: Float) = update { prefs ->
    prefs[floatPreferencesKey("letterSpacing")] = spacing
  }
  
  fun updateLineHeight(multiplier: Float) = update { prefs ->
    prefs[floatPreferencesKey("lineHeightMultiplier")] = multiplier
  }
  
  fun updateTextAlign(align: String) = update { prefs ->
    prefs[stringPreferencesKey("textAlign")] = align
  }
  
  fun toggleImages(show: Boolean) = update { prefs ->
    prefs[booleanPreferencesKey("showImages")] = show
  }
  
  fun toggleReadableFont(use: Boolean) = update { prefs ->
    prefs[booleanPreferencesKey("useReadableFont")] = use
  }
  
  private fun update(block: suspend (MutablePreferences) -> Unit) {
    viewModelScope.launch {
      dataStore.edit { block(it) }
    }
  }
  
}