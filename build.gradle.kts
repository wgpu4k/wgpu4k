import com.android.utils.TraceUtils.simpleId

plugins {
	alias(libs.plugins.compose.compiler) apply false
	alias(libs.plugins.compose) apply false
}

allprojects {
	tasks.findByName("checkKotlinGradlePluginConfigurationErrors")?.enabled = isAndroidConfigured

	repositories {
		mavenLocal()
		google()
		mavenCentral()
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}


