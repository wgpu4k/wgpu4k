

plugins {
	alias(libs.plugins.kotlinMultiplatform).apply(false)
	alias(libs.plugins.compose.compiler).apply(false)
	alias(libs.plugins.compose).apply(false)
}

allprojects {

	repositories {
		mavenLocal()
		mavenCentral()
		// Use by rococoa
		maven {
			url = uri("http://repo.maven.cyberduck.io.s3.amazonaws.com/releases")
			isAllowInsecureProtocol = true
		}
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}


