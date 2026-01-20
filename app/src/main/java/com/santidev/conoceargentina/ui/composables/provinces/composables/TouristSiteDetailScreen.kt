package com.santidev.conoceargentina.ui.composables.provinces.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.composables.list.TouristSite

// screens/TouristSiteDetailScreen.kt
@Composable
fun TouristSiteDetailScreen(
  site: TouristSite,
  provinceName: String,
  onBack: () -> Unit
) {
  val context = LocalContext.current
  val repository = remember { ProvinceRepository(context) }
  
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    // Imagen de header
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)
    ) {
//      Image(
//        painter = painterResource(
//          repository.getImageResource(site.imageSites)
//        ),
//        contentDescription = site.name,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier.fillMaxSize()
//      )
      
      // Botón atrás sobre la imagen
      IconButton(
        onClick = onBack,
        modifier = Modifier
          .align(Alignment.TopStart)
          .padding(16.dp)
          .statusBarsPadding()
          .background(
            color = Color.Black.copy(alpha = 0.5f),
            shape = CircleShape
          )
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Volver",
          tint = Color.White
        )
      }
    }
    
    // Contenido con superficie redondeada
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 300.dp)
        .offset(y = (-40).dp),
      shape = RoundedCornerShape(
        topStart = 28.dp,
        topEnd = 28.dp
      ),
      color = Color.White,
      shadowElevation = 8.dp
    ) {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Título
        item {
          Column {
            Text(
              text = site.name,
              fontSize = 28.sp,
              fontWeight = FontWeight.Bold,
              color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF127FEC),
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = provinceName,
                fontSize = 14.sp,
                color = Color(0xFF127FEC),
                fontWeight = FontWeight.Medium
              )
            }
          }
        }
        
        // Descripción
        item {
          Text(
            text = site.description,
            fontSize = 16.sp,
            color = Color(0xFF64748B),
            lineHeight = 24.sp
          )
        }
        
        // Espacio al final
        item {
          Spacer(modifier = Modifier.height(40.dp))
        }
      }
    }
  }
}