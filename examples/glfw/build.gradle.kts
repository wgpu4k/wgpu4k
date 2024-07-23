plugins {
	kotlin("jvm")
	application
}

val windowsLib = getLibraryProject()
	.projectDir
	.resolve("src")
	.resolve("jvmMain")
	.resolve("resources")
	.resolve("win32-x86-64")

dependencies {
	implementation(projects.examples.common)
}

application {
	mainClass.set("io.ygdrasil.wgpu.examples.GlfwMainKt")
	if (Platform.os == Os.MacOs) {
		applicationDefaultJvmArgs += "-XstartOnFirstThread"
	}

	applicationDefaultJvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"
	applicationDefaultJvmArgs += "--enable-native-access=ALL-UNNAMED"
	//applicationDefaultJvmArgs += "-Djextract.trace.downcalls=true"
}

kotlin {
	compilerOptions {
		allWarningsAsErrors = true
	}
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(22))
	}
}

fun getLibraryProject() = projects.wgpu4k.identityPath.path
	?.let(::project) ?: error("Could not find project path")