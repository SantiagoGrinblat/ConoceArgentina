package com.santidev.conoceargentina.ui.composables.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.santidev.conoceargentina.R

@Composable
fun BackgroundImage() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Transparent)
  ) {
    Image(
      painter = painterResource(id = R.drawable.fondo),
      contentDescription = "Background Image App",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxSize()
        .blur(radius = 1.dp)
    )
  }
}