
plugins {
	alias(libs.plugins.compose.compiler) apply false
	alias(libs.plugins.compose) apply false
}

allprojects {

	repositories {
		mavenCentral()
		// Snapshot repository
		maven {
			url = uri("https://gitlab.com/api/v4/projects/25805863/packages/maven")
		}
		google()
		mavenLocal()
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}


