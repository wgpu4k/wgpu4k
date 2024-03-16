plugins {
	alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
	js {
		binaries.executable()
		browser()
		nodejs()
		generateTypeScriptDefinitions()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":examples:web-js"))
			}
		}

	}
}

