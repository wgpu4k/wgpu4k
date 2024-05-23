plugins {
	alias(libs.plugins.kotlinMultiplatform)
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
				api(projects.wgpu4k)
				api(libs.coroutines)
				api(libs.korge.foundation)
				api(libs.korge.core)
			}
		}

		val jvmMain by getting {
			dependencies {
				api(projects.librococoa)
			}
		}
	}

	compilerOptions {
		allWarningsAsErrors = true
	}
}