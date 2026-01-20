package com.santidev.conoceargentina.ui.composables.list

data class CardItem(
  val name: String,
  val info: String,
  val image: Int,
  val region: String,
  val icons: List<Int> = emptyList(),
  val population: String? = null,
  val maximumAltitude: String? = null,
  val history: String? = null,
  val relevantData: String? = null,
  val geography: String? = null,
  val capital: String? = null,
  val size: String? = null,
  val touristSites: List<TouristSite> = emptyList()
)