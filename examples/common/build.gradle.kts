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
				api("com.soywiz.korge:korge-foundation:5.4.0")
				api("com.soywiz.korge:korge-core:5.4.0")
			}
		}

		val jvmMain by getting {
			dependencies {
				api(projects.librococoa)
			}
		}
	}
}