package com.santidev.conoceargentina.ui.composables.favorites.data

import android.content.Context
import android.util.Log
import com.santidev.conoceargentina.ui.composables.provinces.data.AppDataBase
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(context: Context) {
  
  private val database = AppDataBase.getDatabase(context)
  private val favoriteDao = database.favoriteDao()
  
  // Obtener todos los favoritos
  fun getAllFavorites(): Flow<List<FavoriteEntity>> {
    return favoriteDao.getAllFavorites()
  }
  
  // Verificar si es O no favorito
  fun isFavorite(provinceName: String): Flow<Boolean> {
    return favoriteDao.isFavorite(provinceName)
  }
  
  // Agregar a favoritos
  suspend fun addFavorite(provinceName: String) {
    try {
      Log.d("FavoriteRepository", "Agregando a favoritos: $provinceName")
      favoriteDao.addFavorite(FavoriteEntity(provinceName = provinceName))
    } catch (e: Exception) {
      Log.e("FavoriteRepository", "Error agregando favorito", e)
    }
  }
  
  // Eliminar de favoritos
  suspend fun removeFavorite(provinceName: String) {
    try {
      Log.d("FavoriteRepository", "Eliminando de favoritos: $provinceName")
      favoriteDao.removeFavoriteByName(provinceName)
    } catch (e: Exception) {
      Log.e("FavoriteRepository", "Error eliminando favorito", e)
    }
  }
  
  // Versión mejorada de toggleFavorite
  suspend fun toggleFavorite(provinceName: String, isCurrentlyFavorite: Boolean) {
    try {
      if (isCurrentlyFavorite) {
        Log.d("FavoriteRepository", "Eliminando favorito: $provinceName")
        favoriteDao.removeFavoriteByName(provinceName)
      } else {
        Log.d("FavoriteRepository", "Agregando favorito: $provinceName")
        favoriteDao.addFavorite(FavoriteEntity(provinceName = provinceName))
      }
    } catch (e: Exception) {
      Log.e("FavoriteRepository", "Error en toggle", e)
    }
  }
  
}