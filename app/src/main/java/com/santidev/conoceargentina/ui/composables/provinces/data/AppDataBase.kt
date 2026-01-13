package com.santidev.conoceargentina.ui.composables.provinces.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santidev.conoceargentina.ui.composables.favorites.data.FavoriteDao
import com.santidev.conoceargentina.ui.composables.favorites.data.FavoriteEntity

@Database(entities = [ProvincesEntity::class, FavoriteEntity::class], version = 8, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
  
  abstract fun provinceDao(): ProvinceDao
  abstract fun favoriteDao(): FavoriteDao
  
  companion object {
    @Volatile
    private var INSTANCE: AppDataBase? = null
    
    fun getDatabase(context: Context): AppDataBase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDataBase::class.java,
          "conoce_argentina_db"
        )
          .fallbackToDestructiveMigration(true)
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
  
}