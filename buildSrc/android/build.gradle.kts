plugins {
	`kotlin-dsl`
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
}

dependencies {
	implementation(libs.android.library)
}
