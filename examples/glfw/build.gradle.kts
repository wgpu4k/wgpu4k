plugins {
	kotlin("jvm")
	application
}

dependencies {
	implementation(projects.wgpu4kScenes)
	implementation(libs.wgpu4k.native)
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
		languageVersion.set(JavaLanguageVersion.of(22))
	}
}