plugins {
	`kotlin-dsl`
	kotlin("plugin.serialization") version "2.0.20"
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
}

dependencies {
	implementation(libs.download)
	implementation(libs.kotlin.multiplatform)
	implementation(libs.jreleaser.plugin)
	implementation(libs.android.library)

	implementation(libs.gson)
	implementation(libs.zip4j)
	implementation(libs.okhttp)
	implementation(libs.commons.io)

	implementation("com.charleskorn.kaml:kaml:0.66.0")

}
