package com.santidev.conoceargentina.navigation

import androidx.navigation3.runtime.NavKey

sealed class Routes: NavKey {
  
  data object Home: Routes()
  
  data object List: Routes() { //UI de la lista de provincias
    data class ProvinceDetail(val provinceName: String) : Routes() //nevgacion interna dentro de la UI por cada CARD
  }
  
  data object Favorites: Routes()
  
  data object Error: Routes()
  
}