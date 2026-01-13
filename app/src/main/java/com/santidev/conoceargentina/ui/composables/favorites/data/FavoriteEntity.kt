package com.santidev.conoceargentina.ui.composables.favorites.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
  @PrimaryKey
  val provinceName: String, //Como no tienen ids, se usa el nombre como ids unicos.
  val addedAt: Long = System.currentTimeMillis()
)
