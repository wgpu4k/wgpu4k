import com.android.utils.TraceUtils.simpleId

plugins {
	alias(libs.plugins.compose.compiler) apply false
	alias(libs.plugins.compose) apply false
}

tasks.findByName("checkKotlinGradlePluginConfigurationErrors").also {
	if (isAndroidConfigured) {
		println("android is configured will keep checkKotlinGradlePluginConfigurationErrors $it")
	} else {
		println("android is not configured will disable checkKotlinGradlePluginConfigurationErrors $it")
	}
	it?.enabled = isAndroidConfigured
}

allprojects {

	repositories {
		mavenLocal()
		google()
		mavenCentral()
	}

	group = "io.ygdrasil"
	version = System.getenv("VERSION")?.takeIf { it.isNotBlank() } ?: "0.0.0-SNAPSHOT"
}


