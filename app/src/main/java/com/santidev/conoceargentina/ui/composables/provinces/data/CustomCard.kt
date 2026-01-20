package com.santidev.conoceargentina.ui.composables.provinces.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.CardItem

@Composable
fun CustomCard(items: CardItem, onClick: () -> Unit) {
  Card(
    onClick = onClick,
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color(0xFFEEE4D2)
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      
      Image(
        painter = painterResource(items.image),
        contentDescription = items.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(100.dp)
          .clip(RoundedCornerShape(8.dp))
      )
      
      Spacer(modifier = Modifier.width(8.dp))
      
      Column(
        modifier = Modifier.weight(1f)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          
          Text(
            text = items.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
          )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Box(
          modifier = Modifier
            .background(
              color = Color(0xFF64748B).copy(alpha = 0.1f),
              shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
        ) {
          Text(
            text = items.region,
            fontSize = 14.sp,
            color = Color(0xFF94A3B8),
            fontWeight = FontWeight.Bold,
          )
        }
        
        Text(
          text = items.info,
          fontSize = 14.sp,
          color = Color(0xFF64748B),
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items.icons.forEach { iconRes ->
            Icon(
              painter = painterResource(iconRes),
              contentDescription = null,
              tint = Color(0xFF94A3B8),
              modifier = Modifier.size(20.dp)
            )
          }
        }
      }
      
      Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = "Ver más",
        tint = Color(0xFF127FEC),
        modifier = Modifier.size(24.dp)
      )
    }
  }
}