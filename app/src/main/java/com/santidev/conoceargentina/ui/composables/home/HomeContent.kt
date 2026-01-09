package com.santidev.conoceargentina.ui.composables.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.utils.LocalStrings

@Composable
fun HomeContent(onShowClick: (Routes) -> Unit) {
  
  val strings = LocalStrings.current
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 8.dp),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(modifier = Modifier.padding(bottom = 10.dp)) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = strings.welcomeTo,
          fontSize = 45.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White,
        )
        Text(
          text = strings.argentina,
          fontSize = 45.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF7CD1FA),
        )
      }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Text(
      text = strings.description,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
      color = Color.White,
      style = TextStyle(lineHeight = 26.sp),
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(8.dp))
    Button(
      onClick = { onShowClick(Routes.List) },
      modifier = Modifier.padding(6.dp),
      colors = ButtonDefaults.buttonColors(Color(0xFF127FEC)),
      shape = RoundedCornerShape(8.dp),
      
      ) {
      Text(
        text = strings.explore,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.width(4.dp))
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
        contentDescription = "Arrow Down",
        tint = Color.White,
        modifier = Modifier.size(25.dp)
      )
    }
  }
}