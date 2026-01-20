package com.santidev.conoceargentina.ui.composables.list

import android.content.Context
import android.util.Log
import com.santidev.conoceargentina.R
import com.santidev.conoceargentina.ui.composables.provinces.data.AppDataBase
import com.santidev.conoceargentina.ui.composables.provinces.data.ProvincesEntity
import org.json.JSONArray

class ProvinceRepository(private val context: Context) {
  
  private val database = AppDataBase.getDatabase(context)
  private val provinceDao = database.provinceDao()
  
  suspend fun initializeDatabase(language: String) {
    try {
      Log.d("ProvinceRepository", "=== INICIO initializeDatabase ===")
      Log.d("ProvinceRepository", "Idioma solicitado: $language")
      
      // para verificar si ya hay datos
      val existing = provinceDao.getAllProvinces(language)
      Log.d("ProvinceRepository", "Provincias existentes en DB: ${existing.size}")
      
      if (existing.isNotEmpty()) {
        Log.d("ProvinceRepository", "DB ya tiene datos para $language")
        return
      }
      
      Log.d("ProvinceRepository", "Inicializando DB para idioma: $language")
      
      // para verificar que archivo JSON se va a cargar
      val resourceId = when (language) {
        "EN" -> R.raw.provinces_en
        "ES" -> R.raw.provinces_es
        "PT" -> R.raw.provinces_pt
        "FR" -> R.raw.provinces_fr
        else -> R.raw.provinces_es
      }
      
      Log.d("ProvinceRepository", "Resource ID: $resourceId")
      
      // como se lee el JSON
      val jsonString = context.resources
        .openRawResource(resourceId)
        .bufferedReader()
        .use { it.readText() }
      
      Log.d("ProvinceRepository", "JSON leído, longitud: ${jsonString.length}")
      Log.d("ProvinceRepository", "Primeros 200 caracteres: ${jsonString.take(200)}")
      
      // Parsea
      val entities = parseJsonToEntities(jsonString, language)
      Log.d("ProvinceRepository", "Entities parseadas: ${entities.size}")
      
      // Inserta
      provinceDao.insertAll(entities)
      Log.d("ProvinceRepository", "✅ Insertadas ${entities.size} provincias")
      
    } catch (e: Exception) {
      Log.e("ProvinceRepository", "❌ Error inicializando DB", e)
      Log.e("ProvinceRepository", "Tipo de error: ${e.javaClass.simpleName}")
      Log.e("ProvinceRepository", "Mensaje: ${e.message}")
      e.printStackTrace()
    }
  }
  
  private fun parseJsonToEntities(jsonString: String, language: String): List<ProvincesEntity> {
    val jsonArray = JSONArray(jsonString)
    val entities = mutableListOf<ProvincesEntity>()
    
    Log.d("ProvinceRepository", "Parseando JSON, total items: ${jsonArray.length()}")
    
    for (i in 0 until jsonArray.length()) {
      try {
        val item = jsonArray.getJSONObject(i)
        val name = item.getString("name")
        
        val touristSitesJson = if (item.has("touristSites")) {
          item.getJSONArray("touristSites").toString()
        } else {
          null
        }
        
        Log.d("ProvinceRepository", "Parseando: $name")
        entities.add(
          ProvincesEntity(
            name = item.getString("name"),
            info = item.getString("info"),
            image = item.getString("image"),
            region = item.getString("region"),
            language = language,
            population = item.optString("population"),
            maximumAltitude = item.optString("maximumAltitude"),
            history = item.optString("history"),
            relevantData = item.optString("relevantData"),
            geography = item.optString("geography"),
            capital = item.optString("capital"),
            size = item.optString("size"),
            touristSites = touristSitesJson
          )
        )
        Log.d("ProvinceRepository", "✅ $name parseado correctamente")
      } catch (e: Exception) {
        Log.e("ProvinceRepository", "Error parseando item $i", e)
      }
    }//verifica si se tomo correctamente O no los nombres de los json
    Log.d("ProvinceRepository", "Total entities creadas: ${entities.size}")
    return entities
  }
  
  // Obtener todas las provincias
  suspend fun getAllProvinces(language: String): List<CardItem> {
    initializeDatabase(language)
    return provinceDao.getAllProvinces(language).map { it.toCardItem() }
  }
  
  // Filtrar segun la region
  suspend fun getProvincesByRegion(language: String, region: String): List<CardItem> {
    initializeDatabase(language)
    return if (region == "all") {
      provinceDao.getAllProvinces(language).map { it.toCardItem() }
    } else {
      provinceDao.getProvincesByRegion(language, region).map { it.toCardItem() }
    }
  }
  
  //TODO: Buscar provincias a futuro (quizas)
  suspend fun searchProvinces(language: String, query: String): List<CardItem> {
    initializeDatabase(language)
    return provinceDao.searchProvinces(language, query).map { it.toCardItem() }
  }
  
  // Convertir Entity a CardItem
  private fun ProvincesEntity.toCardItem(): CardItem {
    return CardItem(
      name = this.name,
      info = this.info,
      image = getImageResource(this.image),
      region = this.region,
      icons = emptyList(),
      population = this.population,
      maximumAltitude = this.maximumAltitude,
      history = this.history,
      relevantData = this.relevantData,
      geography = this.geography,
      capital = this.capital,
      size = this.size,
      touristSites = parseTouristSites(this.touristSites)
    )
  }
  
  internal fun getImageResource(imageName: String): Int {
    val resId = context.resources.getIdentifier(
      imageName,
      "drawable",
      context.packageName
    )
    
    return if (resId == 0) {
      Log.w("ProvinceRepository", "Imagen no encontrada: $imageName")
      android.R.drawable.ic_menu_gallery
    } else {
      resId
    }
  }
  
  private fun parseTouristSites(jsonString: String?): List<TouristSite> {
    if (jsonString == null) return emptyList()
    
    return try {
      val jsonArray = JSONArray(jsonString)
      List(jsonArray.length()) { index ->
        val site = jsonArray.getJSONObject(index)
        TouristSite(
          name = site.getString("name"),
          description = site.getString("description"),
//          imageSites = site.getString("imageSites")
        )
      }
    } catch (e: Exception) {
      Log.e("ProvinceRepository", "Error parseando sitios turísticos", e)
      emptyList()
    }
  }
}