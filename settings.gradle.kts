enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
  val kotlinVersion: String by settings
  val bintrayVersion: String by settings

  plugins {
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("com.jfrog.bintray") version bintrayVersion
  }
  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "org.jetbrains.kotlin.plugin.serialization" -> useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version ?: kotlinVersion}")
      }
    }
  }
}

rootProject.name = "spring-kotlinx-serialization"

include("spring-kotlinx-serialization-codec")
include("spring-kotlinx-serialization-autoconfigure")
include("spring-kotlinx-serialization-starter-webflux")
