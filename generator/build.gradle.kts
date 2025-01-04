plugins {
	`kotlin-dsl`
	kotlin("plugin.serialization") version "2.1.0"
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
	mavenLocal()
	//wgpu4k snapshot & preview repository
	maven("https://gitlab.com/api/v4/projects/25805863/packages/maven")
}

dependencies {

	implementation(libs.gson)
	implementation(libs.zip4j)
	implementation(libs.commons.io)

	implementation(libs.kaml)

	implementation(libs.wgpu.specs)
}
