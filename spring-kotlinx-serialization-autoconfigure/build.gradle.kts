import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
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
  annotationProcessor(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
  implementation(platform("org.springframework:spring-framework-bom:$springVersion"))

  implementation(kotlin("stdlib-jdk8"))

  implementation("org.springframework.boot:spring-boot")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")

  compileOnly(project(":spring-kotlinx-serialization-codec"))
  compileOnly("org.springframework:spring-webflux")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

tasks.register<Jar>("sourcesJar") {
  from(sourceSets.main.get().allJava)
  archiveClassifier.set("sources")
}

publishing {
  val mavenJava by publications.creating(MavenPublication::class) {
    from(components["java"])
    artifact(tasks["sourcesJar"])
  }
}
