import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  publishing
  `maven-publish`
}

val springVersion: String by project
val serializationRuntimeVersion: String by project
val reactorVersion: String by project

dependencies {
  implementation(platform("org.springframework:spring-framework-bom:$springVersion"))
  implementation(platform("io.projectreactor:reactor-bom:$reactorVersion"))

  api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationRuntimeVersion")

  implementation("org.springframework:spring-web")
  implementation("io.projectreactor:reactor-core")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

java {
  targetCompatibility = JavaVersion.VERSION_1_8

  withSourcesJar()
}

publishing {
  val mavenJava by publications.creating(MavenPublication::class) {
    from(components["java"])
  }
}
