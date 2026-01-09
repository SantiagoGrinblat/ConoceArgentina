package com.santidev.conoceargentina.ui.screens

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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.composables.list.CardItem
import com.santidev.conoceargentina.ui.composables.list.ProvinceRepository
import com.santidev.conoceargentina.ui.composables.provinces.generalInfo.GeographySection
import com.santidev.conoceargentina.ui.composables.provinces.generalInfo.KeyDataSection
import com.santidev.conoceargentina.ui.composables.provinces.generalInfo.SectionHistory
import com.santidev.conoceargentina.ui.utils.LocalStrings

@Composable
fun ProvinceDetailScreen(provinceName: String, onBack: () -> Unit) {
  
  val context = LocalContext.current
  val currentLanguage = LocalContext.current
  val repository = remember { ProvinceRepository(context) }
  
  var province by remember { mutableStateOf<CardItem?>(null) }
  var isLoading by remember { mutableStateOf(true) }
  
  // Cargar datos de la provincia especifica segun el nombre sin importar el lenguaje
  LaunchedEffect(provinceName, currentLanguage) {
    isLoading = true
    val allProvinces = repository.getAllProvinces(currentLanguage.toString())
    province = allProvinces.find { it.name == provinceName }
    isLoading = false
  }
  
  if (isLoading) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      CircularProgressIndicator()
    }
    return
  }
  
  if (province == null) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text("Provincia no encontrada")
    }
    return
  }
  
  Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.White)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
    ) {
      Image(
        painter = painterResource(province!!.image),
        contentDescription = province!!.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
      
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .statusBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {

        IconButton(
          onClick = onBack,
          modifier = Modifier
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
        
        Row {

          IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
              .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = CircleShape
              )
          ) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "Compartir",
              tint = Color.White
            )
          }
          
          Spacer(modifier = Modifier.width(8.dp))
          
          IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
              .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = CircleShape
              )
          ) {
            Icon(
              imageVector = Icons.Default.FavoriteBorder,
              contentDescription = "Favorito",
              tint = Color.White
            )
          }
        }
      }
    }
    
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 350.dp)
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
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        item {
          Column() {
            Text(
              text = province!!.name,
              fontSize = 32.sp,
              fontWeight = FontWeight.Bold,
              color = Color.Black
            )
            
            Row {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF127FEC),
                modifier = Modifier.size(24.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "ARGENTINA",
                fontSize = 18.sp,
                color = Color(0xFF127FEC),
                fontWeight = FontWeight.Bold
              )
            }
            
          }
        }
        
        item {
          Text(
            text = province!!.info,
            fontSize = 18.sp,
            color = Color(0xFF64748B),
            lineHeight = 24.sp
          )
        }
        
        item {
          KeyDataSection(province = province!!)
        }
        
        item {
          SectionHistory(province = province!!)
        }
        
        item {
          GeographySection(province = province!!)
        }
        
        item {
          Button(
            onClick = { /* TODO */ },
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF127FEC)
            ),
            shape = RoundedCornerShape(12.dp)
          ) {
            Icon(
              imageVector = Icons.Default.CalendarToday,
              contentDescription = null,
              tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Planear Viaje",
              color = Color.White,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
  
}