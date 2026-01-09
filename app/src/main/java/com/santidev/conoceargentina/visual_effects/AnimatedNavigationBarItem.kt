package com.santidev.lugarapp.ui.visual_effects

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.santidev.conoceargentina.navigation.BottonNavItem

@Composable
fun RowScope.AnimatedNavigationBarItem(
  navItem: BottonNavItem,
  selected: Boolean,
  onClick: () -> Unit,
  selectedColor: Color = Color(0xFF127FEC),
  unselectedColor: Color = Color(0xFF94A3B8)
) {
  // Animaciones
  val scale by animateFloatAsState(
    targetValue = if (selected) 1.1f else 1f,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioMediumBouncy,
      stiffness = Spring.StiffnessLow
    ),
    label = "scale"
  )
  
  val iconColor by animateColorAsState(
    targetValue = if (selected) selectedColor else unselectedColor,
    animationSpec = tween(300),
    label = "iconColor"
  )
  
  
  NavigationBarItem(
    icon = {
      Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          
          Icon(
            navItem.icon,
            contentDescription = navItem.label,
            tint = iconColor,
            modifier = Modifier.scale(scale)
          )
        }
      }
    },
    label = {
      Text(
        navItem.label,
        color = iconColor
      )
    },
    selected = selected,
    onClick = onClick,
    colors = NavigationBarItemDefaults.colors(
      selectedIconColor = selectedColor,
      selectedTextColor = selectedColor,
      indicatorColor = Color.Transparent,
      unselectedIconColor = unselectedColor,
      unselectedTextColor = unselectedColor
    )
  )
}