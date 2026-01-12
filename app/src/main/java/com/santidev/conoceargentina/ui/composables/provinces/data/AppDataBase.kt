package com.santidev.conoceargentina.ui.composables.provinces.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProvincesEntity::class], version = 7, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
  
  abstract fun provinceDao(): ProvinceDao
  
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