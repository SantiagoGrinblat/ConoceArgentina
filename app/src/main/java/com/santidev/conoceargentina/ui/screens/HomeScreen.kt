package com.santidev.conoceargentina.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.ui.composables.HomeHeader
import com.santidev.conoceargentina.ui.composables.home.BackgroundImage
import com.santidev.conoceargentina.ui.composables.home.HomeContent
import com.santidev.conoceargentina.ui.utils.LocalStrings

@Composable
fun HomeScreen(onNavigate: (Routes) -> Unit) {
  
  val string = LocalStrings.current
  
  Box(modifier = Modifier.fillMaxSize()) {
    
    BackgroundImage()
    
    Column(
      modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars)
    ) {
      HomeHeader()
      
      HomeContent(onShowClick = {
        onNavigate(Routes.List)
      })
    }
    
  }
}