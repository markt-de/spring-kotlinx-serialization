import java.time.LocalDate
import java.time.format.DateTimeFormatter

plugins {
  kotlin("jvm") apply false
  `maven-publish`
  `publishing`
  id("com.jfrog.bintray")
}

val kotlinVersion: String by project

allprojects {
  version = "0.0.1-${kotlinVersion}-SNAPSHOT"
  group = "de.classyfi.libs"

  repositories {
    jcenter()
  }
} 

val isSnapshot = project.version.toString().endsWith("-SNAPSHOT")

if (isSnapshot) {
  version = "${project.version.toString().removeSuffix("-SNAPSHOT")}-${LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)}"
}

val bintray_user: String by project
val bintray_apikey: String by project

subprojects {
  version = rootProject.version

  apply(plugin = "com.jfrog.bintray")
  
  bintray {
    user = bintray_user
    key = bintray_apikey
    setPublications("mavenJava")
    with (pkg) {
      repo = "releases"
      name = rootProject.name
      userOrg = "markt-de"
      setLicenses("Apache-2.0")
      vcsUrl = "https://github.com/markt-de/spring-kotlinx-serialization"
      version.name = project.version.toString()

      if (isSnapshot) {
        repo = "snapshots"
        override = true
      }
    }
  }
}
