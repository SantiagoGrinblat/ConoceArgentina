package com.santidev.conoceargentina.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.favorites.data.FavoriteRepository
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.composables.provinces.data.CustomCard
import com.santidev.conoceargentina.ui.utils.LocalLanguage
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.santidev.conoceargentina.navigation.Routes

@Composable
fun FavoriteScreen(onNavigate: (Routes) -> Unit) {
  val context = LocalContext.current
  val currentLanguage = LocalLanguage.current
  val provinceRepository = remember { ProvinceRepository(context) }
  val favoriteRepository = remember { FavoriteRepository(context) }
  
  // Observar favoritos (se actualiza automáticamente)
  val favorites by favoriteRepository.getAllFavorites()
    .collectAsState(initial = emptyList())
  
  var allProvinces by remember { mutableStateOf<List<CardItem>>(emptyList()) }
  var isLoading by remember { mutableStateOf(true) }
  
  // Cargar provincias
  LaunchedEffect(currentLanguage) {
    isLoading = true
    allProvinces = provinceRepository.getAllProvinces(currentLanguage)
    isLoading = false
  }
  
  // Filtrar provincias favoritas
  val favoriteProvinces = remember(favorites, allProvinces) {
    val favoriteNames = favorites.map { it.provinceName }.toSet()
    allProvinces.filter { it.name in favoriteNames }
  }
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFF8FAFC))
  ) {
    // Header
    Text(
      text = "Favoritos",
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(16.dp)
    )
    
    when {
      isLoading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }
      
      favoriteProvinces.isEmpty() -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
              imageVector = Icons.Default.FavoriteBorder,
              contentDescription = null,
              modifier = Modifier.size(64.dp),
              tint = Color(0xFF94A3B8)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = "No tienes favoritos aún",
              fontSize = 18.sp,
              color = Color(0xFF64748B)
            )
          }
        }
      }
      
      else -> {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(vertical = 8.dp)
        ) {
          items(favoriteProvinces) { province ->
            CustomCard(
              items = province,
              onClick = {
                 onNavigate(Routes.List.ProvinceDetail(province.name))
              }
            )
          }
        }
      }
    }
  }
}