import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  publishing
  `maven-publish`
}

group = "de.classyfi.boot"

val springVersion: String by project
val springCloudVersion: String by project
val springBootVersion: String by project

dependencies {
  implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
  implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))

  api(project(":spring-kotlinx-serialization-codec"))
  api(project(":spring-kotlinx-serialization-autoconfigure"))
  api("org.springframework.boot:spring-boot-starter-webflux")
}

publishing {
  val mavenJava by publications.creating(MavenPublication::class) {
    from(components["java"])
  }
}
