plugins {
	`kotlin-dsl`
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
}

dependencies {
    implementation(libs.bundles.korlibs)
	implementation(libs.coroutines)

	implementation(libs.okhttp)
	implementation(libs.zip4j)
	implementation(libs.commons.io)
	implementation(libs.dokka)

}
