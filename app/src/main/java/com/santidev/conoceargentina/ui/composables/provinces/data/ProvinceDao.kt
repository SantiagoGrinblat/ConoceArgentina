package com.santidev.conoceargentina.ui.composables.provinces.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProvinceDao {
  
  @Query("SELECT * FROM provinces WHERE language = :language")
  suspend fun getAllProvinces(language: String): List<ProvincesEntity>
  
  @Query("SELECT * FROM provinces WHERE language = :language AND region = :region")
  suspend fun getProvincesByRegion(language: String, region: String): List<ProvincesEntity>
  
  @Query("SELECT * FROM provinces WHERE language = :language AND (name LIKE '%' || :query || '%' OR info LIKE '%' || :query || '%')")
  suspend fun searchProvinces(language: String, query: String): List<ProvincesEntity>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(provinces: List<ProvincesEntity>)
  
  @Query("DELETE FROM provinces WHERE language = :language")
  suspend fun deleteByLanguage(language: String)
  
}