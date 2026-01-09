package com.santidev.conoceargentina.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.composables.HomeHeader
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.composables.provinces.data.CustomCard
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.utils.LocalLanguage
import com.santidev.conoceargentina.ui.utils.RegionFilters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(onNavigate: (Routes) -> Unit) {
  
  val context = LocalContext.current
  val currentLanguage = LocalLanguage.current
  val repository = remember { ProvinceRepository(context) }
  
  var provinces by remember { mutableStateOf<List<CardItem>>(emptyList()) }
  var selectedRegion by remember { mutableStateOf("all") }
  var isLoading by remember { mutableStateOf(true) }
  
  // Cargar provincias
  LaunchedEffect(currentLanguage, selectedRegion) {
    isLoading = true
    Log.d("ListScreen", "Cargando provincias - idioma: $currentLanguage, región: $selectedRegion")
    
    provinces = repository.getProvincesByRegion(currentLanguage, selectedRegion)
    
    Log.d("ListScreen", "Provincias cargadas: ${provinces.size}")
    provinces.forEach {
      Log.d("ListScreen", "- ${it.name}")
    }
    
    isLoading = false
  }
  
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HomeHeader()
    
    Spacer(modifier = Modifier.height(10.dp))
    
    RegionFilters(
      selectedRegion = selectedRegion,
      onRegionSelected = { selectedRegion = it }
    )
    
    if (isLoading) {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    } else {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
      ) {
        items(provinces) { province ->
          CustomCard(
            items = province,
            onClick = {
              Log.d("ListScreen", "Click en: ${province.name}")
              onNavigate(Routes.List.ProvinceDetail(province.name))
            }
          )
        }
      }
    }
    
  }
}