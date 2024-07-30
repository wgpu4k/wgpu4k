plugins {
	kotlin("jvm")
	id("publish")
}

dependencies {
	implementation(libs.jna)
	implementation(libs.jnaPlatform)
	implementation("cglib:cglib:3.3.0")
}

kotlin {
	compilerOptions {
		allWarningsAsErrors = true
	}
}

val buildNativeLibraryTask = tasks.register<Exec>("buildNativeLibrary") {
	commandLine(
		"xcodebuild",
		"-project",
		"rococoa.xcodeproj",
		"-target",
		"librococoa-test",
		"-configuration",
		"Release"
	)
}

val copyNativeLibraryTask = tasks.register<Copy>("copyFile") {
	dependsOn(buildNativeLibraryTask)
	from("build/Release/librococoa.dylib")  // source file
	into("src/main/resources/darwin")  // destination directory
}

tasks.named("processResources") {
	if (Platform.os == Os.MacOs) dependsOn(copyNativeLibraryTask)
}


