plugins {
	alias(libs.plugins.kotlinMultiplatform)
	kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
	js {
		binaries.executable()
		browser()
		generateTypeScriptDefinitions()
	}
	jvm()


	sourceSets {

		all {
			languageSettings.optIn("kotlin.ExperimentalStdlibApi")
			languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
			languageSettings.optIn("kotlin.js.ExperimentalJsExport")
		}

		val commonMain by getting {
			dependencies {
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0-RC")
				api(projects.wgpu4kToolkit)
				api(libs.coroutines)
				api(libs.korge.foundation)
				api(libs.korge.core)
			}
		}

	}

	compilerOptions {
		// TODO fiw warning and uncomment
		//allWarningsAsErrors = true
	}
}