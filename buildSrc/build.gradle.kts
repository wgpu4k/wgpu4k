plugins {
	`kotlin-dsl`
	alias(libs.plugins.kotlin.serialization)
}


repositories {
	gradlePluginPortal()
	google()
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.asProvider().get()}")
	implementation("com.android.tools.build:gradle:${libs.versions.agp.get()}")
	implementation("io.kotest:kotest-framework-plugin-gradle:${libs.versions.kotest.get()}")
	implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:${libs.versions.ksp.get()}")

    implementation(libs.bundles.korlibs)
	implementation(libs.coroutines)

	implementation("com.vanniktech:gradle-maven-publish-plugin:${libs.versions.maven.publish.get()}")
	implementation(libs.dokka)

}
