plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.santidev.conoceargentina"
  compileSdk = 36
  
  defaultConfig {
    applicationId = "com.santidev.conoceargentina"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  // Navigation
  implementation(libs.navigation.runtime)
  implementation(libs.navigation.ui)
  
  // Material Icons
  implementation(libs.androidx.material.icons.extended)
  
  // JSON
  implementation(libs.google.gson)
  
  // Room
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)
  
  // Coroutines
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)
  
  // Core Android
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  
  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  
  // DataStore
  implementation(libs.androidx.datastore.preferences)
  
  // ViewModel
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  
  // ========== TESTS ==========
  
  // Unit Tests
  testImplementation(libs.junit)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlinx.coroutines.test)
  
  // Android Tests
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.mockk.android)
  androidTestImplementation(libs.androidx.room.testing)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  
  // Debug
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  testImplementation(kotlin("test"))
}