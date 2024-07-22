plugins {
	`kotlin-dsl`
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
	maven( url = "https://jitpack.io" )
}

dependencies {
	implementation(libs.download)
	implementation("io.ktor:ktor-server-core-jvm:2.3.12")
	implementation("io.ktor:ktor-server-netty-jvm:2.3.12")
	implementation("com.microsoft.playwright:playwright:1.45.0")
    implementation(libs.bundles.korlibs)
	implementation(libs.coroutines)
	implementation(libs.kotlin.multiplatform)
	implementation(libs.android.library)
	implementation(libs.jreleaser.plugin)
}
