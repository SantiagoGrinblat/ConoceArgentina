package com.santidev.conoceargentina.ui.composables.provinces.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.composables.list.TouristSite

@Composable
fun TouristSiteCard(
  site: TouristSite,
  repository: ProvinceRepository,
  onClick: () -> Unit
) {
  Card(
    onClick = onClick,
    modifier = Modifier
      .width(200.dp)
      .height(180.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(2.dp)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      // Imagen de fondo
//      Image(
//        painter = painterResource(
//          repository.getImageResource(site.imageSites)
//        ),
//        contentDescription = site.name,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier.fillMaxSize()
//      )
      
      // Gradient overlay
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.Black.copy(alpha = 0.7f)
              )
            )
          )
      )
      
      // Contenido sobre la imagen
      Column(
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(12.dp)
      ) {
        Text(
          text = site.name,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
          text = site.description,
          fontSize = 12.sp,
          color = Color.White.copy(alpha = 0.8f)
        )
      }
    }
  }
}