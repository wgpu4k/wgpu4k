plugins {
	kotlin("jvm")
	application
}

dependencies {
	implementation(projects.wgpu4kScenes)
	implementation(libs.wgpu4k.native)
	testImplementation(libs.bundles.kotest)
	testImplementation(libs.kotest.runner.junit5)
}

application {
	mainClass.set("io.ygdrasil.webgpu.examples.GlfwMainKt")
	if (Platform.os == Os.MacOs) {
		applicationDefaultJvmArgs += "-XstartOnFirstThread"
	}

	applicationDefaultJvmArgs += "--add-opens=java.base/java.lang=ALL-UNNAMED"
	applicationDefaultJvmArgs += "--enable-native-access=ALL-UNNAMED"
}

kotlin {
	compilerOptions {
		allWarningsAsErrors = true
	}
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(25))
	}
}

tasks.test {
	useJUnitPlatform()
}