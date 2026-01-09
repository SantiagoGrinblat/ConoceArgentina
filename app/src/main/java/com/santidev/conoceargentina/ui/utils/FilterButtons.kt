package com.santidev.conoceargentina.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterButton(
  text: String,
  selected: Boolean,
  onClick: () -> Unit
) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
      containerColor = if (selected) Color(0xFF127FEC) else Color.Transparent
    ),
    shape = RoundedCornerShape(50.dp),
    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
    border = if (selected) {
      BorderStroke(1.dp, Color(0xFF127FEC))
    } else {
      BorderStroke(1.dp, Color(0x92797979))
    },
  ) {
    Text(
      text = text,
      color = if (selected) Color.White else Color.Black,
      fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
      fontSize = 14.sp
    )
  }
}