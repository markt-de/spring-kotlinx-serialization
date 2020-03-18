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

  implementation(kotlin("stdlib-jdk8"))
  api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationRuntimeVersion")

  implementation("org.springframework:spring-web")
  implementation("io.projectreactor:reactor-core")
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
