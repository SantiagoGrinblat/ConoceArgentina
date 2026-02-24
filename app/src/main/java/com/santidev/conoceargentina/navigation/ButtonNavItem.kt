package com.santidev.conoceargentina.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottonNavItem(
  val routes: Routes,
  val icon: ImageVector,
  val label: String,
) {
  object List : BottonNavItem(Routes.List, Icons.AutoMirrored.Filled.FormatListBulleted, "Lista de Provincias")
  object Favorites : BottonNavItem(Routes.Favorites, Icons.Default.Favorite, "Favoritos")
  object Settings : BottonNavItem(Routes.Settings, Icons.Default.AccessibilityNew, "Accesibilidad")
}

val bottomNavItems = listOf(
  BottonNavItem.List,
  BottonNavItem.Favorites,
  BottonNavItem.Settings
)

fun Routes.isBottomNavRoute(): Boolean {
  return when (this) {
    Routes.List, Routes.Favorites, Routes.Settings -> true
    else -> false
  }
}