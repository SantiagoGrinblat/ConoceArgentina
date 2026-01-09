package com.santidev.conoceargentina.ui.utils

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegionFilters(
  selectedRegion: String,  // Recibe el estado
  onRegionSelected: (String) -> Unit  // Callback para cambiar el estado
) {
  val strings = LocalListStrings.current
  
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .horizontalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    
    // Todas
    FilterButton(
      text = strings.allRegions,
      selected = selectedRegion == "all",
      onClick = { onRegionSelected("all") }
    )
    
    // NOA (NOR OESTE ARGENTINO)
    FilterButton(
      text = strings.noa,
      selected = selectedRegion == "NOA",
      onClick = { onRegionSelected("NOA") }
    )
    
    // NEA (NOR ESTE ARGENTINO)
    FilterButton(
      text = strings.nea,
      selected = selectedRegion == "NEA",
      onClick = { onRegionSelected("NEA") }
    )
    
    // Cuyo
    FilterButton(
      text = strings.cuyo,
      selected = selectedRegion == "CUYO",
      onClick = { onRegionSelected("CUYO") }
    )
    
    // Centro
    FilterButton(
      text = strings.center,
      selected = selectedRegion == "CENTRO",
      onClick = { onRegionSelected("CENTRO") }
    )
    
    // Patagonia
    FilterButton(
      text = strings.patagonia,
      selected = selectedRegion == "PATAGONIA",
      onClick = { onRegionSelected("PATAGONIA") }
    )
    
    // Pampeana
    FilterButton(
      text = strings.pampeana,
      selected = selectedRegion == "PAMPEANA",
      onClick = { onRegionSelected("PAMPEANA") }
    )
  }
}
