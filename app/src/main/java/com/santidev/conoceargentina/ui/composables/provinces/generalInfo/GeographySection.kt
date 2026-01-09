package com.santidev.conoceargentina.ui.composables.provinces.generalInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.utils.LocalListStrings

@Composable
fun GeographySection(province: CardItem) {
  val strings = LocalListStrings.current
  
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      VerticalDivider(
        modifier = Modifier.height(24.dp),
        thickness = 4.dp,
        color = Color(0xFF127FEC)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = strings.geographySection,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.Black
      )
    }
    
    
    province.geography?.let { geography ->
      Text(
        text = geography,
        fontSize = 16.sp,
        color = Color(0xFF64748B),
        lineHeight = 22.sp
      )
    }
    
  }
}