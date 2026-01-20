package com.santidev.conoceargentina.navigation.core

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.santidev.conoceargentina.navigation.Routes
import com.santidev.conoceargentina.navigation.bottomNavItems
import com.santidev.conoceargentina.navigation.isBottomNavRoute
import com.santidev.conoceargentina.ui.composables.provinces.composables.TouristSiteDetailScreen
import com.santidev.conoceargentina.ui.screens.FavoriteScreen
import com.santidev.conoceargentina.ui.screens.HomeScreen
import com.santidev.conoceargentina.ui.screens.ListScreen
import com.santidev.conoceargentina.ui.screens.ProvinceDetailScreen
import com.santidev.lugarapp.ui.visual_effects.AnimatedNavigationBarItem

@Composable
fun NavWrapper() {
  val backStack = remember { mutableStateListOf<Any>(Routes.Home) }
  
  Box(modifier = Modifier.fillMaxWidth()) {
    Scaffold(
      modifier = Modifier.fillMaxWidth(),
      containerColor = Color(0xFFEEE6D7),
      bottomBar = {
        val currentRoutes = backStack.lastOrNull() as? Routes
        if (currentRoutes?.isBottomNavRoute() == true) {
          Column {
            HorizontalDivider(
              modifier = Modifier.fillMaxWidth(),
              thickness = 0.5.dp,
              color = Color(0x92797979)
            )
            NavigationBar(
              containerColor = Color.Transparent
            ) {
              bottomNavItems.forEach { item ->
                AnimatedNavigationBarItem(
                  navItem = item,
                  selected = currentRoutes == item.routes,
                  onClick = {
                    backStack.clear()
                    backStack.add(item.routes)
                  }
                )
              }
            }
          }
        }
      }
    ) { paddingValues ->
      Box(
        modifier = Modifier
          .fillMaxSize()
      ) {
        NavDisplay(
          backStack = backStack,
          onBack = { backStack.removeLastOrNull() },
          entryProvider = entryProvider {
            
            entry<Routes.Home> {
              HomeScreen(
                onNavigate = { route -> backStack.add(route) }
              )
            }
            
            entry<Routes.List> {
              Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                ListScreen(
                  onNavigate = { route -> backStack.add(route) }
                )
              }
            }
            
            //Se pone Routes.List.ProvinceDetail, navegacion dentro de list
            //porque tengo que entrar al ProvinceDetail que esta adentro del objeto List que esta en el archivo Routes
            entry<Routes.List.ProvinceDetail> { backStackEntry ->
              val provinceName = backStackEntry.provinceName
              ProvinceDetailScreen(
                provinceName = provinceName,
                onBack = { backStack.removeLastOrNull() },
                onNavigate = { route -> backStack.add(route) }
              )
            }
            
            entry<Routes.List.ProvinceDetail.TouristSiteDetail> { backStackEntry ->
              val tourist = backStackEntry.site as Routes.List.ProvinceDetail.TouristSiteDetail
              TouristSiteDetailScreen(
                site = tourist.site,
                provinceName = tourist.provinceName,
                onBack = { backStack.removeLastOrNull() }
              )
            }
            
            entry<Routes.Favorites> {
              Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                FavoriteScreen(
                  onNavigate = { route -> backStack.add(route) }
                )
              }
            }
            
            entry<Routes.Error> {
              Text("Error")
            }
          },
          transitionSpec = {
            slideInHorizontally(
              initialOffsetX = { it },
              animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
              targetOffsetX = { -it },
              animationSpec = tween(250)
            )
          },
          popTransitionSpec = {
            slideInHorizontally(
              initialOffsetX = { -it },
              animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
              targetOffsetX = { it },
              animationSpec = tween(250)
            )
          },
          predictivePopTransitionSpec = {
            slideInHorizontally(
              initialOffsetX = { -it },
              animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
              targetOffsetX = { it },
              animationSpec = tween(250)
            )
          }
        )
      }
    }
  }
}