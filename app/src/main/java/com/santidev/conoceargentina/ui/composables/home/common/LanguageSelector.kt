package com.santidev.conoceargentina.ui.composables.home.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.conoceargentina.ui.utils.LocalLanguage
import com.santidev.conoceargentina.ui.utils.LocalLanguageChanger

@Composable
fun LanguageSelector() {
  
  val currentLanguage = LocalLanguage.current
  val changeLanguage = LocalLanguageChanger.current
  var expanded by remember { mutableStateOf(false) }
  
  val languages = listOf(
    "ES" to "Español 🇪🇸",
    "EN" to "English 🇺🇸",
    "PT" to "Português 🇧🇷",
    "FR" to "Français 🇫🇷"
  )
  
  Box {
    Button(
      onClick = { expanded = true },
      border = BorderStroke(1.dp, Color.Gray),
      colors = ButtonDefaults.buttonColors(
        containerColor = Color.Black.copy(alpha = 0.4f)
      ),
      shape = RoundedCornerShape(50.dp),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
      Text(
        text = currentLanguage,
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.width(4.dp))
      Icon(
        imageVector = Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(20.dp)
      )
    }
    
    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      languages.forEach { (code, name) ->
        DropdownMenuItem(
          text = { Text(name) },
          onClick = {
            changeLanguage(code)
            expanded = false
          },
          leadingIcon = {
            if (code == currentLanguage) {
              Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
              )
            }
          }
        )
      }
    }
  }
}