package com.santidev.conoceargentina.ui.composables.provinces.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "provinces")
data class ProvincesEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val name: String,
  val info: String,
  val image: String,
  val region: String,
  val language: String,
  val population: String? = null,
  val maximumAltitude: String? = null,
  val history: String? = null,
  val relevantData: String? = null,
  val geography: String? = null,
  val capital: String? = null,
  val size: String? = null,
  val touristSites: String? = null
)
