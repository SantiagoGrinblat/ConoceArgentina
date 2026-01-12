package com.santidev.conoceargentina.ui.data.local

object ListStrings {
  
  data class Strings(
    val title: String,
    val searchPlaceholder: String,
    val allRegions: String,
    val center: String,
    val pampeana: String,
    val cuyo: String,
    val noa: String,
    val nea: String,
    val patagonia: String,
    
    //sections
    val keyDataSection: String,
    val geographySection: String,
    val historySection: String,
    
    // Tabs del bottom bar
    val list: String,
    val map: String,
    val favorites: String,
    val settings: String
  )
  
  val spanish = Strings(
    title = "Lista de Provincias",
    searchPlaceholder = "Buscar provincia...",
    allRegions = "TODAS",
    center = "CENTRO",
    pampeana = "PAMPEANA",
    cuyo = "CUYO",
    noa = "NOA",
    nea = "NEA",
    patagonia = "PATAGONIA",
    
    //sections
    keyDataSection = "Datos Clave",
    geographySection = "Geografía",
    historySection = "Historia",
    
    list = "Lista",
    map = "Mapa",
    favorites = "Favoritos",
    settings = "Ajustes"
  )
  
  val english = Strings(
    title = "Province List",
    searchPlaceholder = "Search province...",
    allRegions = "ALL",
    center = "CENTRO",
    pampeana = "PAMPEAN",
    cuyo = "CUYO",
    noa = "NOA",
    nea = "NEA",
    patagonia = "PATAGONIA",
    
    keyDataSection = "Key Data",
    geographySection = "Geography",
    historySection = "History",
    
    list = "List",
    map = "Map",
    favorites = "Favorites",
    settings = "Settings"
  )
  
  val portuguese = Strings(
    title = "Lista de Províncias",
    searchPlaceholder = "Buscar província...",
    allRegions = "TODAS",
    center = "CENTRO",
    pampeana = "PAMPEANA",
    cuyo = "CUYO",
    noa = "NOA",
    nea = "NEA",
    patagonia = "PATAGÔNIA",
    
    keyDataSection = "Dados-chave",
    geographySection = "Geografía",
    historySection = "História",
    
    list = "Lista",
    map = "Mapa",
    favorites = "Favoritos",
    settings = "Configurações"
  )
  
  val french = Strings(
    title = "Liste des provinces",
    searchPlaceholder = "Rechercher une province...",
    allRegions = "TOUTES",
    center = "CENTRE",
    pampeana = "PAMPÉENNE",
    cuyo = "CUYO",
    noa = "NOA",
    nea = "NEA",
    patagonia = "PATAGONIE",
    
    keyDataSection = "Données clés",
    geographySection = "Géographié",
    historySection = "Historie",
    
    list = "Liste",
    map = "Carte",
    favorites = "Favoris",
    settings = "Paramètres"
  )
  
  
  fun getStrings(language: String): Strings {
    return when (language) {
      "EN" -> english
      "ES" -> spanish
      "PT" -> portuguese
      "FR" -> french
      else -> spanish
    }
  }
}