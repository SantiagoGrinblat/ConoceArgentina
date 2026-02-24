package com.santidev.conoceargentina.navigation

import androidx.navigation3.runtime.NavKey
import com.santidev.conoceargentina.ui.composables.list.TouristSite

sealed class Routes: NavKey {
  
  data object Home: Routes()
  
  data object List: Routes() {
    data class ProvinceDetail(val provinceName: String) : Routes(){
      data class TouristSiteDetail(
        val site: TouristSite,
        val provinceName: String
      ) : Routes() // navegacion dentro de cada card de cada provincia
    } //nevgacion interna dentro de la UI por cada CARD
  } //UI de la lista de provincias
  
  data object Favorites: Routes()
  
  data object Settings: Routes()
  
  data object Error: Routes()
  
}