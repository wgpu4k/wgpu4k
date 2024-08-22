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
	implementation(libs.ktor.server.core.jvm)
	implementation(libs.ktor.server.netty.jvm)
	implementation(libs.playwright)
    implementation(libs.bundles.korlibs)
	implementation(libs.coroutines)
	implementation(libs.kotlin.multiplatform)
	implementation(libs.android.library)
	implementation(libs.jreleaser.plugin)
}

val isAndroidConfigured: Boolean
	get() = System.getenv("ANDROID_HOME") != null
