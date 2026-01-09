package com.santidev.conoceargentina.ui.composables.provinces.generalInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.composables.provinces.composables.StatCard
import com.santidev.conoceargentina.ui.utils.LocalListStrings
import com.santidev.conoceargentina.ui.utils.LocalStrings

// composables/detail/KeyDataSection.kt
@Composable
fun KeyDataSection(province: CardItem) {
  
  val strings = LocalListStrings.current
  
  // Stats (Población y Altitud)
  if (province.capital != null || province.population != null ||
    province.maximumAltitude != null || province.size != null) {
    
    Box(modifier = Modifier.fillMaxWidth()) {
      Row {
        VerticalDivider(modifier = Modifier.height(24.dp), thickness = 4.dp, color = Color.Blue)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = strings.keyDataSection,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp
        )
      }
    }
    
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Primera fila
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        province.population?.let { pop ->
          Box(modifier = Modifier.weight(1f)) {
            StatCard(
              icon = Icons.Default.Person,
              label = "Población",
              value = pop
            )
          }
        }
        
        province.maximumAltitude?.let { alt ->
          Box(modifier = Modifier.weight(1f)) {
            StatCard(
              icon = Icons.Default.Landscape,
              label = "Altitud Máx",
              value = alt
            )
          }
        }
      }
      
      // Segunda fila
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        province.capital?.let { cap ->
          Box(modifier = Modifier.weight(1f)) {
            StatCard(
              icon = Icons.Default.LocationCity,
              label = "Capital",
              value = cap
            )
          }
        }
        
        province.size?.let { size ->
          Box(modifier = Modifier.weight(1f)) {
            StatCard(
              icon = Icons.Default.DensitySmall,
              label = "Superficie",
              value = size
            )
          }
        }
      }
    }
  }
}