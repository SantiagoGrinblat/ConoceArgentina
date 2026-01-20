package com.santidev.conoceargentina.ui.composables.provinces.generalInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.composables.list.TouristSite
import com.santidev.conoceargentina.ui.composables.provinces.composables.TouristSiteCard
import com.santidev.conoceargentina.ui.utils.LocalListStrings

@Composable
fun TouristSitesSection(
  province: CardItem,
  onSiteClick: (TouristSite) -> Unit,
) {
  val strings = LocalListStrings.current
  val context = LocalContext.current
  val repository = remember { ProvinceRepository(context) }
  
  if (province.touristSites.isEmpty()) return
  
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    // Header con título y "Ver todos"
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        VerticalDivider(
          modifier = Modifier.height(24.dp),
          thickness = 4.dp,
          color = Color(0xFF127FEC)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = strings.touristSitesSection,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp,
          color = Color.Black
        )
      }
      
      // Botón "Ver todos" (opcional)
      TextButton(onClick = { /* Navegar a lista completa */ }) {
        Text(
          text = "Ver todos",
          color = Color(0xFF127FEC),
          fontSize = 14.sp
        )
        Icon(
          imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
          contentDescription = null,
          tint = Color(0xFF127FEC),
          modifier = Modifier.size(20.dp)
        )
      }
    }
    
    // Scroll horizontal de sitios
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
      items(province.touristSites) { site ->
        TouristSiteCard(
          site = site,
          repository = repository,
          onClick = { onSiteClick(site) }
        )
      }
    }
  }
}