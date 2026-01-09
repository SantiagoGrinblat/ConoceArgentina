package com.santidev.conoceargentina.ui.data.local

object StringResources {
  
  data class Strings(
    val welcome: String,
    val welcomeTo: String,
    val argentina: String,
    val description: String,
    val explore: String,
    val location: String,
    val selectLanguage: String
  )
  
  val spanish = Strings(
    welcome = "Bienvenido",
    welcomeTo = "Bienvenido a",
    argentina = "Argentina",
    description = """Descubrí la belleza de nuestras tierras,
desde los desiertos del norte
hasta los hielos del sur.""",
    explore = "Explorar",
    location = "ARGENTINA",
    selectLanguage = "Seleccionar idioma"
  )
  
  val english = Strings(
    welcome = "Welcome",
    welcomeTo = "Welcome to",
    argentina = "Argentina",
    description = """Discover the beauty of our lands,
from the northern deserts
to the southern ice.""",
    explore = "Explore",
    location = "ARGENTINA",
    selectLanguage = "Select language"
  )
  
  val portuguese = Strings(
    welcome = "Bem-vindo",
    welcomeTo = "Bem-vindo a",
    argentina = "Argentina",
    description = """Descubra a beleza de nossas terras,
dos desertos do norte
ao gelo do sul.""",
    explore = "Explorar",
    location = "ARGENTINA",
    selectLanguage = "Selecionar idioma"
  )
  
  val french = Strings(
    welcome = "Bienvenue",
    welcomeTo = "Bienvenue en",
    argentina = "Argentine",
    description = """Découvrez la beauté de nos terres,
des déserts du nord
aux glaces du sud.""",
    explore = "Explorer",
    location = "ARGENTINE",
    selectLanguage = "Sélectionner la langue"
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