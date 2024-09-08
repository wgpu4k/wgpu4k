
plugins {
	alias(libs.plugins.compose.compiler) apply false
	alias(libs.plugins.compose) apply false
}

allprojects {
	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}


